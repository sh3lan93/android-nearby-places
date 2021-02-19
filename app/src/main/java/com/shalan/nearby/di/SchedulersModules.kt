package com.shalan.nearby.di

import com.shalan.nearby.base.services.SchedulerService
import com.shalan.nearby.base.services.SchedulerServiceImp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import org.koin.core.qualifier.named
import org.koin.dsl.module


val schedulersModule = module {

    single { SchedulerServiceImp() as SchedulerService }
    single(named(name = "mainthread")) { AndroidSchedulers.mainThread() as Scheduler }
    single(named(name = "io")) { IoScheduler() as Scheduler }
    single(named(name = "computation")) { ComputationScheduler() as Scheduler }
}
