package com.shalan.nearby.di

import com.shalan.nearby.base.services.SerializationService
import com.shalan.nearby.base.services.SerializationServiceImp
import org.koin.dsl.module


val serializationModule = module {
    single { SerializationServiceImp(moshi = get()) as SerializationService }
}
