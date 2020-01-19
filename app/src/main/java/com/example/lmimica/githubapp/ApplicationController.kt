package com.example.lmimica.githubapp

import android.app.Application
import com.example.lmimica.githubapp.di.presenterModule
import com.example.lmimica.githubapp.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ApplicationController : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ApplicationController)
            modules(listOf(presenterModule, retrofitModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}