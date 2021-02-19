package com.shalan.nearby.main

import com.shalan.nearby.base.repo.IRepo
import com.shalan.nearby.common.SharedPrefConstants
import com.shalan.nearby.enums.LocationUpdateType

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class MainRepo : IRepo {


    fun whichType(): LocationUpdateType = if (getSharedPreferences().get(
            SharedPrefConstants.LOCATION_UPDATE_TYPE_KEY,
            LocationUpdateType.REALTIME.type
        ) == LocationUpdateType.REALTIME.type
    ) LocationUpdateType.REALTIME else LocationUpdateType.SINGLE


    fun changeLocationType(type: Int) {
        getSharedPreferences().save(SharedPrefConstants.LOCATION_UPDATE_TYPE_KEY, type)
    }


}