package com.shalan.nearby.nearby_places

import com.shalan.nearby.base.repo.IRepo
import com.shalan.nearby.common.SharedPrefConstants
import com.shalan.nearby.database.dao.VenueDAO
import com.shalan.nearby.enums.LocationUpdateType
import com.shalan.nearby.network.response.Venue
import com.shalan.nearby.network.services.NearbyService
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesListingRepo(private val service: NearbyService, private val venueDAO: VenueDAO) :
    IRepo {

    fun fetchRecommendation(userLocation: String, isNetworkAvailable: Boolean) =
        if (isNetworkAvailable) loadFromNetwork(userLocation) else loadFromDatabase()


    private fun loadFromDatabase(): Single<List<Venue>> =
        venueDAO.fetchSavedData()

    private fun loadFromNetwork(userLocation: String) =
        service.getNearbyPlaces(userLocation = userLocation).map {
            it.response.groups.first().items.map {
                it.venue
            }
        }.doOnSubscribe {
            venueDAO.nukeTable()
        }.doOnSuccess {
            venueDAO.insertData(it)
        }

    fun whichLocationType() = if (getSharedPreferences().get(
            SharedPrefConstants.LOCATION_UPDATE_TYPE_KEY,
            LocationUpdateType.REALTIME.type
        ) == LocationUpdateType.REALTIME.type
    ) LocationUpdateType.REALTIME else LocationUpdateType.SINGLE
}