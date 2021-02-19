package com.shalan.nearby.di

import com.shalan.nearby.base.services.SharedService
import com.shalan.nearby.base.services.SharedServiceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val otherServices = module {
    single { SharedServiceImp(androidContext()) as SharedService }
}
