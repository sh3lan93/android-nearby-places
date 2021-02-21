package com.shalan.nearby.base.utils.rx

import com.shalan.nearby.base.services.SchedulerService
import io.reactivex.rxjava3.core.*
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Mohamed Shalan on 2/21/21
 */

class IoTransformers<T>() : ObservableTransformer<T, T>,
    SingleTransformer<T, T>,
    CompletableTransformer, MaybeTransformer<T, T>, KoinComponent {

    private val schedulerService by inject<SchedulerService>()

    override fun apply(upstream: Observable<T>): ObservableSource<T> =
        upstream.subscribeOn(schedulerService.getIoScheduler())
            .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Single<T>): SingleSource<T> =
        upstream.subscribeOn(schedulerService.getIoScheduler())
            .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Completable): CompletableSource =
        upstream.subscribeOn(schedulerService.getIoScheduler())
            .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Maybe<T>): MaybeSource<T> =
        upstream.subscribeOn(schedulerService.getIoScheduler())
            .observeOn(schedulerService.getMainThread())
}