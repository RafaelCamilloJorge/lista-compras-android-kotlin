package com.example.listadecompras

import android.app.Application
import com.example.listadecompras.injection_dependencie.DependencyInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(DependencyInitializer().appModule)
        }
    }
}
