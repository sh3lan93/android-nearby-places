package com.shalan.nearby.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.shalan.nearby.R
import com.shalan.nearby.base.activity.BaseActivity
import com.shalan.nearby.databinding.ActivityMainBinding
import com.shalan.nearby.enums.LocationUpdateType

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreateInit(savedInstance: Bundle?) {
        setSupportActionBar(binding.toolbar)
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments
            ?: emptyList())
            fragment.onActivityResult(requestCode, resultCode, data)
    }
}