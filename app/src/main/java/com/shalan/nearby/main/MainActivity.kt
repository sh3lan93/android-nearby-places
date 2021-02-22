package com.shalan.nearby.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shalan.nearby.R
import com.shalan.nearby.base.activity.BaseActivity
import com.shalan.nearby.databinding.ActivityMainBinding
import com.shalan.nearby.enums.LocationUpdateType
import com.shalan.nearby.nearby_places.NearbyPlacesListingFragment

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    private lateinit var navController: NavController

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreateInit(savedInstance: Bundle?) {
        setSupportActionBar(binding.toolbar)
        navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.informaticFragment -> binding.toolbar.visibility = GONE
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.location_update_type)?.let {
            if (viewmodel.getLocationUpdateType() == LocationUpdateType.REALTIME)
                it.title = getString(R.string.single_txt)
            else
                it.title = getString(R.string.realtime_txt)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.location_update_type -> changeToTheOtherMode()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeToTheOtherMode() {
        if (viewmodel.getLocationUpdateType() == LocationUpdateType.REALTIME) {
            viewmodel.updateLocationType(LocationUpdateType.SINGLE.type)
        } else {
            viewmodel.updateLocationType(LocationUpdateType.REALTIME.type)
        }
        invalidateOptionsMenu()
        if (navController.currentDestination?.id == R.id.nearbyPlacesListingFragment){
            (supportFragmentManager.fragments.find { it is NavHostFragment }?.childFragmentManager?.fragments?.first() as NearbyPlacesListingFragment?)?.changeMode()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments
            ?: emptyList())
            fragment.onActivityResult(requestCode, resultCode, data)
    }
}