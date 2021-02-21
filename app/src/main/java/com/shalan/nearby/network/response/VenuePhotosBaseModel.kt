package com.shalan.nearby.network.response

import com.squareup.moshi.JsonClass

/**
 * Created by Mohamed Shalan on 2/21/21
 */


@JsonClass(generateAdapter = true)
data class VenuePhotos(val response: VenuePhotosResponse)

@JsonClass(generateAdapter = true)
data class VenuePhotosResponse(val photos: Photos)

@JsonClass(generateAdapter = true)
data class Photos(val items: List<PhotoData>)

@JsonClass(generateAdapter = true)
data class PhotoData(val prefix: String, val suffix: String) {

    fun getFullPhotoUrl() = "${prefix}500x500${suffix}"
}