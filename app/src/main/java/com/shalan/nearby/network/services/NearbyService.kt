package com.shalan.nearby.network.services

import com.shalan.nearby.BuildConfig
import com.shalan.nearby.network.response.Explore
import com.shalan.nearby.network.response.VenuePhotos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mohamed Shalan on 2/20/21
 */

interface NearbyService {

    @GET("venues/explore")
    fun getNearbyPlaces(
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("ll") userLocation: String,
        @Query("v") version: String = "20210219",
        @Query("radius") radius: Int = 1000
    ): Single<Explore>

    @GET("venues/{venue_id}/photos")
    fun getVenuePhotos(
        @Path("venue_id") venueId: String,
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("v") version: String = "20210219"
    ): Single<VenuePhotos>
}