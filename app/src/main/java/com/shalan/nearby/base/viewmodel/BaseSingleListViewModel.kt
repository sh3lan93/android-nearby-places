package com.shalan.nearby.base.states

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shalan.nearby.base.viewmodel.BaseViewModel
import io.reactivex.rxjava3.core.Single


abstract class BaseSingleListViewModel<T> : BaseViewModel() {

    var refreshData: Boolean = false

    private val data: MutableLiveData<IResult<T>> by lazy {
        MutableLiveData<IResult<T>>()
    }

    val data_: LiveData<IResult<T>>
        get() = data

    override fun startLogic() {
        super.startLogic()
        if (refreshData || data.value?.fetchData() == null) {
            loadData().execute(data)
            refreshData = false
        }
    }

    abstract fun loadData(): Single<T>

}
