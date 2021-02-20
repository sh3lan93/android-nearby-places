package com.shalan.nearby.nearby_places

import com.shalan.nearby.base.repo.IRepo
import com.shalan.nearby.common.SharedPrefConstants
import com.shalan.nearby.enums.LocationUpdateType
import com.shalan.nearby.network.services.NearbyService

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesListingRepo(private val service: NearbyService) : IRepo {

    fun fetchRecommendation(userLocation: String) =
        service.getNearbyPlaces(userLocation = userLocation).map {
            it.response.groups.first().items
        }


    fun whichLocationType() = if (getSharedPreferences().get(
            SharedPrefConstants.LOCATION_UPDATE_TYPE_KEY,
            LocationUpdateType.REALTIME.type
        ) == LocationUpdateType.REALTIME.type
    ) LocationUpdateType.REALTIME else LocationUpdateType.SINGLE
}