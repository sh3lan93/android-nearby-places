package com.shalan.nearby.base.services

import com.squareup.moshi.Moshi


class SerializationServiceImp(private val moshi: Moshi) : SerializationService {

    override fun <T> serialize(value: T, clazz: Class<T>): String {
        val adapter = moshi.adapter(clazz)
        return adapter.toJson(value)
    }

    override fun <T> deserialize(value: String, clazz: Class<T>): T? {
        val adapter = moshi.adapter(clazz)
        return adapter.fromJson(value)
    }


}
