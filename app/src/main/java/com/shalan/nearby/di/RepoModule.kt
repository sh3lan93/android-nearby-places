package com.shalan.nearby.di

import com.shalan.nearby.main.MainRepo
import com.shalan.nearby.nearby_places.NearbyPlacesListingRepo
import org.koin.dsl.module

val repoModule = module {

    factory { MainRepo() }

    factory { NearbyPlacesListingRepo(service = get(), venueDAO = get()) }
}
