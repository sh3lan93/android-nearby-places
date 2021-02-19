package com.shalan.nearby.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import com.shalan.nearby.base.services.SchedulerService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.get


interface IViewModel : KoinComponent, LifecycleObserver {

    fun getDisposable(): CompositeDisposable = get()

    fun getSchedulerService(): SchedulerService = get()

    fun startLogic()
}
