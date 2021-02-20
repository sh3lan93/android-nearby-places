package com.shalan.nearby.network.response

import com.squareup.moshi.JsonClass

/**
 * Created by Mohamed Shalan on 2/20/21
 */

@JsonClass(generateAdapter = true)
data class Explore(val response: ExploreResponse)


@JsonClass(generateAdapter = true)
data class ExploreResponse(val groups: List<ResponseGroup>)

@JsonClass(generateAdapter = true)
data class ResponseGroup(val type: String, val name: String, val items: List<GroupItem>)

@JsonClass(generateAdapter = true)
data class GroupItem(val id: String, val name: String, val location: ItemLocation)

@JsonClass(generateAdapter = true)
data class ItemLocation(val address: String, val city: String, val state: String) {

    fun getDisplayedAddress() = "$address, $city, $state"

}