package com.vivek.rapidapi

import android.app.Application
import android.content.Context
import com.vivek.rapidapi.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CountryApplication : Application() {

    companion object {
        @JvmStatic
        var appContext: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                listOf(
                    allModules
                )
            )
        }
    }
}
