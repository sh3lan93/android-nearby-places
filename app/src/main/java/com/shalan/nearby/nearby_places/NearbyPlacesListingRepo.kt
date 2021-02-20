package com.shalan.nearby.nearby_places

import com.shalan.nearby.base.repo.IRepo
import com.shalan.nearby.network.services.NearbyService

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesListingRepo(private val service: NearbyService) : IRepo {

    fun fetchRecommendation(userLocation: String) =
        service.getNearbyPlaces(userLocation = userLocation).map {
            it.response.groups.first().items
        }
}