package com.shalan.nearby.di

import androidx.room.Room
import com.shalan.nearby.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 2/21/21
 */


val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "nearby_places_database")
            .build()
    }

    factory {
        get<AppDatabase>().venueDao()
    }
}