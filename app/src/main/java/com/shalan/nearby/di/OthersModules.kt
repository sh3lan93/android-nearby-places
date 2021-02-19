package com.shalan.nearby.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module


val othersModules = module {
	factory {
		CompositeDisposable()
	}
}
