package com.shalan.nearby.base.network

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent


class DefaultInterceptor : Interceptor, KoinComponent {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .method(originalRequest.method, originalRequest.body)
            .build()
        return chain.proceed(newRequest)
    }
}
