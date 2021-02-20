package com.shalan.nearby.di

import com.shalan.nearby.main.MainViewModel
import com.shalan.nearby.nearby_places.NearbyPlacesListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewmodelModules = module {
    viewModel { MainViewModel(repo = get()) }
    viewModel { NearbyPlacesListingViewModel(repo = get()) }
}
