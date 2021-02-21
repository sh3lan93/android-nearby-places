package com.shalan.nearby.database

import androidx.room.TypeConverter
import com.shalan.nearby.network.response.ItemLocation

/**
 * Created by Mohamed Shalan on 2/22/21
 */


class VenueTypeConverter {

    @TypeConverter
    fun toString(value: ItemLocation?): String? = value?.let {
        "${it.address},${it.city},${it.state}"
    }

    @TypeConverter
    fun toObject(value: String?): ItemLocation? = value?.let {
        val stringArray = it.split(",")
        ItemLocation(address = stringArray[0], city = stringArray[1], state = stringArray[2])
    }
}