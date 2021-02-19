package com.shalan.nearby.base.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.shalan.nearby.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.get
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkImp(context: Context) : INetwork {

    private val client: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(DefaultInterceptor())
            addInterceptor(ChuckerInterceptor(context))
            if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply {
				level = HttpLoggingInterceptor.Level.BODY
			})

        }.build()

        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
				MoshiConverterFactory.create(get())
			)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    override fun getClient(): Retrofit = client

    override fun <T> createService(clazz: Class<T>): T = client.create(clazz)
}
