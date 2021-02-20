package com.shalan.nearby.nearby_places

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
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
    private lateinit var locationRequest: LocationRequest
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationUpdatesCallback by lazy {
        object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                Log.d(
                    TAG,
                    "onLocationResult: ${p0.lastLocation.latitude}, ${p0.lastLocation.longitude}"
                )
            }
        }
    }

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

    override fun onResume() {
        super.onResume()
        checkLocationPermission()
    }

    override fun onStop() {
        super.onStop()
        fusedLocationClient.removeLocationUpdates(locationUpdatesCallback)
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
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
        if (checkIsGoogleServiceAvailable())
            if (viewmodel.isRealtimeUpdates()) {
                handleRealtimeType()
            }
    }

    private fun checkIsGoogleServiceAvailable(): Boolean =
        GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext())
            .let { status ->
                if (status != ConnectionResult.SUCCESS) {
                    if (GoogleApiAvailability.getInstance().isUserResolvableError(status)) {
                        GoogleApiAvailability.getInstance().getErrorDialog(
                            requireActivity(),
                            status,
                            GOOGLE_SERVICE_AVAILABILITY_REQUEST_CODE, {
                                it.dismiss()
                                findNavController().navigate(
                                    MainNavXmlDirections.actionGlobalInformaticFragment(
                                        message = getString(R.string.google_service_not_available)
                                    )
                                )
                            }
                        )?.show()
                    }
                }
                status == ConnectionResult.SUCCESS
            }

    private fun handleRealtimeType() {
        locationRequest = LocationRequest.create().apply {
            interval = 15000
            fastestInterval = 10000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            .also { locationSettingBuilder ->
                val settingClient = LocationServices.getSettingsClient(requireActivity())
                val locationSettingResponse =
                    settingClient.checkLocationSettings(locationSettingBuilder.build())

                locationSettingResponse.addOnSuccessListener { settingsResponse ->
                    getUserLocation()
                }

                locationSettingResponse.addOnFailureListener { settingsException ->
                    if (settingsException is ResolvableApiException) {
                        try {
                            settingsException.startResolutionForResult(
                                requireActivity(),
                                CHECK_LOCATION_SETTINGS_REQUEST_CODE
                            )
                        } catch (intentException: SendIntentException) {
                            Log.e(TAG, "handleRealtimeType: ${intentException.message}")
                        }
                    } else {
                        findNavController().navigate(
                            MainNavXmlDirections.actionGlobalInformaticFragment(
                                message = getString(R.string.something_went_wrong)
                            )
                        )
                    }
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationUpdatesCallback,
            Looper.getMainLooper()
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHECK_LOCATION_SETTINGS_REQUEST_CODE) {
            if (resultCode != RESULT_OK)
                findNavController().navigate(
                    MainNavXmlDirections.actionGlobalInformaticFragment(
                        getString(
                            R.string.location_not_enabled_message
                        )
                    )
                )
            else
                getUserLocation()
        }
    }

    companion object {
        val TAG = NearbyPlacesListingFragment::class.java.simpleName
        val GOOGLE_SERVICE_AVAILABILITY_REQUEST_CODE = 1
        val CHECK_LOCATION_SETTINGS_REQUEST_CODE = 2
    }
}