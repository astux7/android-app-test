package com.basta.demo

import android.app.Application
import com.basta.demo.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoAppApplication)
            modules(KoinModule.allModule())
        }
    }
}