package com.shalan.nearby.di

import com.shalan.nearby.main.MainRepo
import org.koin.dsl.module

val repoModule = module {

    factory { MainRepo() }
}
