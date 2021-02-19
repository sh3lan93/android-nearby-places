package com.shalan.nearby.di

import com.shalan.nearby.base.services.ImageLoadingService
import com.shalan.nearby.base.services.ImageLoadingServiceImp
import org.koin.dsl.module


val imageLoadingModules = module {
    single { ImageLoadingServiceImp() as ImageLoadingService }
}
