package com.shalan.nearby.base.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

interface ISingleListFragment<T, Adapter : RecyclerView.Adapter<*>> {

    fun getRecyclerView(): RecyclerView

    fun getSwipeRefresh(): SwipeRefreshLayout?

    fun showLoading()

    fun showError(error: String?)

    fun hideLoading()

    fun showData(data: T?)

    fun getAdapter(): Adapter

    fun disableSwipeToRefreshDuringLoading() {
        getSwipeRefresh()?.isRefreshing = false
        getSwipeRefresh()?.isEnabled = false
    }

    fun enableSwipeToRefresh() {
        getSwipeRefresh()?.isEnabled = true
    }

}
