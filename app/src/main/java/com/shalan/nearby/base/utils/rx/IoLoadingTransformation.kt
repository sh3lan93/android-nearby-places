package com.shalan.nearby.base.utils.rx

import androidx.lifecycle.MutableLiveData
import com.shalan.nearby.base.services.SchedulerService
import com.shalan.nearby.base.states.IResult
import com.shalan.nearby.base.states.Result
import io.reactivex.rxjava3.core.*

class IoLoadingTransformation<T>(
    private val schedulerService: SchedulerService,
    private val result: MutableLiveData<IResult<T>>
) :
    ObservableTransformer<T, T>,
    SingleTransformer<T, T>,
    CompletableTransformer, MaybeTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Single<T>): SingleSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Completable): CompletableSource = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())

    override fun apply(upstream: Maybe<T>): MaybeSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.getIoScheduler())
        .observeOn(schedulerService.getMainThread())
}
