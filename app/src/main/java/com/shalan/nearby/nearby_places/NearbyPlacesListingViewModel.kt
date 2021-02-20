package com.shalan.nearby.nearby_places

import com.shalan.nearby.base.states.BaseSingleListViewModel
import com.shalan.nearby.enums.LocationUpdateType
import com.shalan.nearby.network.response.GroupItem
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesListingViewModel(private val repo: NearbyPlacesListingRepo) :
    BaseSingleListViewModel<List<GroupItem>>() {


    private lateinit var userLocation: String

    override fun startLogic() {

    }

    fun isFirstTimeToGetLocation() = !::userLocation.isInitialized

    fun getUserLocation(): List<Double> = userLocation.split(",").map { it.toDouble() }

    fun setUserLocation(latitude: Double, longitude: Double) {
        userLocation = "$latitude,$longitude"
        loadData().execute(data)
    }

    override fun loadData(): Single<List<GroupItem>> {
        if (!::userLocation.isInitialized)
            throw RuntimeException("user location should be initialized first")

        return repo.fetchRecommendation(userLocation = userLocation)
    }

    fun isRealtimeUpdates() = repo.whichLocationType() == LocationUpdateType.REALTIME
}