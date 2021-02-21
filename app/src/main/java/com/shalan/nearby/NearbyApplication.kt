package com.shalan.nearby

import androidx.multidex.MultiDexApplication
import com.shalan.nearby.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Level

/**
 * Created by Mohamed Shalan on 2/19/21
 */

class NearbyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR) else EmptyLogger()
            androidContext(this@NearbyApplication)
            modules(
                networkModules,
                othersModules,
                schedulersModule,
                viewmodelModules,
                imageLoadingModules,
                serializationModule,
                otherServices,
                repoModule,
                networkServiceModule,
                databaseModule
            )
        }
    }
}