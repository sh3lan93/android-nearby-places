package com.shalan.nearby.base.network

import org.koin.core.KoinComponent
import retrofit2.Retrofit

interface INetwork : KoinComponent {

	fun getClient(): Retrofit

	fun <T> createService(clazz: Class<T>): T
}
