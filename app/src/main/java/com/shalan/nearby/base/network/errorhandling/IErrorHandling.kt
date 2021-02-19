package com.shalan.nearby.base.network.errorhandling

import io.reactivex.rxjava3.functions.Consumer
import org.koin.core.KoinComponent


interface IErrorHandling : Consumer<Throwable>, KoinComponent
