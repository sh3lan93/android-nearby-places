package com.shalan.nearby.di

import com.shalan.nearby.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


val othersModules = module {
    factory {
        CompositeDisposable()
    }
    single(named("ioexception")) { androidContext().getString(R.string.io_exception) }
    single(named("socketexception")) { androidContext().getString(R.string.socket_exception) }
    single(named("internalserverexception")) { androidContext().getString(R.string.internal_server_exception) }
}
