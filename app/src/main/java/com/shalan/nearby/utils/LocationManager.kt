package com.shalan.nearby.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by Mohamed Shalan on 2/20/21
 */

object LocationManager {

    inline fun checkLocationPermission(
        activity: Activity,
        actionWhenPermissionIsGranted: () -> Unit,
        actionToShowRational: () -> Unit,
        actionToShowPermissionDialog: () -> Unit
    ) {
        when {
            ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> actionWhenPermissionIsGranted.invoke()
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> actionToShowRational.invoke()
            else -> actionToShowPermissionDialog.invoke()
        }
    }
}