package com.shalan.nearby.utils

import android.location.Location

/**
 * Created by Mohamed Shalan on 2/21/21
 */

object LocationUtils {

    fun shouldFetchRecommendations(first: Location, second: Location) =
        first.distanceTo(second) >= 500
}