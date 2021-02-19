package com.shalan.nearby.main

import com.shalan.nearby.base.viewmodel.BaseViewModel

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class MainViewModel(private val repo: MainRepo): BaseViewModel() {

    fun getLocationUpdateType() = repo.whichType()

    fun updateLocationType(type: Int){
        repo.changeLocationType(type)
    }
}