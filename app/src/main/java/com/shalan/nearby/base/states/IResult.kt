package com.shalan.nearby.base.states

interface IResult<T> {

    fun fetchData(): T?

    fun fetchError(): String?

    fun whichStatus(): ICommonStatus
}
