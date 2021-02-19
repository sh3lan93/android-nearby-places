package com.shalan.nearby.base.repo

import com.shalan.nearby.base.services.SerializationService
import com.shalan.nearby.base.services.SharedService
import org.koin.core.KoinComponent
import org.koin.core.get

interface IRepo : KoinComponent {

    fun getSharedPreferences(): SharedService = get()


    fun getSerializationService(): SerializationService = get()

}
