package com.shalan.nearby.di

import com.shalan.nearby.base.network.INetwork
import com.shalan.nearby.base.network.NetworkImp
import com.shalan.nearby.network.services.NearbyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val networkModules = module {

    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

    single {
        NetworkImp(androidContext()) as INetwork
    }
}

val networkServiceModule = module {
    factory { get<INetwork>().createService(NearbyService::class.java) }
}
