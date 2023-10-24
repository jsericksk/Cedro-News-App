package com.kproject.cedronews

import android.app.Application
import com.kproject.cedronews.di.appModule
import com.kproject.cedronews.di.networkModule
import com.kproject.cedronews.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                appModule,
                networkModule,
                repositoryModule
            )
        }
    }
}