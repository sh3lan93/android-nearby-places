package com.shalan.nearby.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalan.nearby.base.network.errorhandling.IErrorHandling
import com.shalan.nearby.base.network.errorhandling.NetworkErrorHandlingImp
import com.shalan.nearby.base.states.IResult
import com.shalan.nearby.base.states.Result
import com.shalan.nearby.base.utils.rx.IoLoadingTransformation
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo

open class BaseViewModel : ViewModel(), IViewModel {

    override fun startLogic() {

    }

    protected fun <T> getIoLoading(result: MutableLiveData<IResult<T>>): IoLoadingTransformation<T> =
        IoLoadingTransformation(getSchedulerService(), result)

    fun <T> Observable<T>.execute(result: MutableLiveData<IResult<T>>) {
        this.compose(getIoLoading(result))
            .subscribe(
                {
                    result.value = Result.success(it)
                },
                NetworkErrorHandlingImp(result = result)
            ).addTo(getDisposable())
    }

    fun <T> Single<T>.execute(result: MutableLiveData<IResult<T>>) {
        this.compose(getIoLoading(result))
            .subscribe(
                {
                    result.value = Result.success(it)
                }, NetworkErrorHandlingImp(result = result)
            ).addTo(getDisposable())
    }

    fun <T> Maybe<T>.execute(
        result: MutableLiveData<IResult<T>>
    ) {
        this.compose(getIoLoading(result))
            .subscribe(
                {
                    result.value = Result.success(it)
                }, NetworkErrorHandlingImp(result = result)
            ).addTo(getDisposable())
    }

    fun Completable.execute(
        result: MutableLiveData<IResult<Any>>
    ) {
        this.compose(getIoLoading(result))
            .subscribe(
                {
                    result.value = Result.success()
                }, NetworkErrorHandlingImp(result = result)
            ).addTo(getDisposable())
    }

    fun <T, Error : IErrorHandling> Single<T>.execute(
        result: MutableLiveData<IResult<T>>,
        error: Error
    ) {
        this.compose(getIoLoading(result))
            .subscribe({
                result.value = Result.success(it)
            }, error)
    }

    override fun onCleared() {
        super.onCleared()
        getDisposable().dispose()
    }

}
