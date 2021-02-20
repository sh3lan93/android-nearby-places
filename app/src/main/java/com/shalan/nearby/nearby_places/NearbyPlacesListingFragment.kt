package com.shalan.nearby.nearby_places

import android.Manifest
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shalan.nearby.MainNavXmlDirections
import com.shalan.nearby.R
import com.shalan.nearby.base.fragment.BaseSingleListFragment
import com.shalan.nearby.databinding.FragmentNearbyPlacesListingBinding
import com.shalan.nearby.network.response.GroupItem
import com.shalan.nearby.utils.DialogsUtils
import com.shalan.nearby.utils.LocationManager

class NearbyPlacesListingFragment :
    BaseSingleListFragment<List<GroupItem>, NearbyPlacesListingViewModel, FragmentNearbyPlacesListingBinding, NearbyPlacesAdapter>(
        layout = R.layout.fragment_nearby_places_listing,
        clazz = NearbyPlacesListingViewModel::class
    ) {


    private lateinit var locationPermissionRequestLauncher: ActivityResultLauncher<String>

    private val nearbyPlacesAdapter by lazy {
        NearbyPlacesAdapter()
    }

    override fun getRecyclerView(): RecyclerView = binding.rvRecommendation

    override fun getSwipeRefresh(): SwipeRefreshLayout? = null

    override fun showLoading() {
        binding.loader.visibility = VISIBLE
        binding.rvRecommendation.visibility = GONE
    }

    override fun showError(error: String?) {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        binding.loader.visibility = GONE
        binding.rvRecommendation.visibility = VISIBLE
    }

    override fun showData(data: List<GroupItem>?) {
        data?.let {
            nearbyPlacesAdapter.submitList(it)
        }
    }

    override fun getAdapter(): NearbyPlacesAdapter = nearbyPlacesAdapter

    override fun onCreateInit(savedInstanceState: Bundle?) {
        observeData()
        getRecyclerView().adapter = getAdapter()
        getRecyclerView().addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        locationPermissionRequestLauncher =
            requireActivity().registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    fetchUserLocation()
                } else {
                    showLocationDeniedMessage()
                }
            }
        checkLocationPermission()
    }

    private fun showLocationDeniedMessage() {
        findNavController().navigate(
            MainNavXmlDirections.actionGlobalInformaticFragment(
                getString(R.string.location_denied_message)
            )
        )
    }

    private fun checkLocationPermission() {
        LocationManager.checkLocationPermission(requireActivity(), actionWhenPermissionIsGranted = {
            fetchUserLocation()
        }, actionToShowRational = {
            DialogsUtils.showRationalDialog(requireContext(), positiveAction = {
                launchLocationPermissionDialog()
            }, negativeAction = {
                showLocationDeniedMessage()
            }, message = R.string.location_permission_reason)
        }, actionToShowPermissionDialog = {
            launchLocationPermissionDialog()
        })
    }

    private fun launchLocationPermissionDialog() {
        locationPermissionRequestLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun fetchUserLocation() {

    }
}