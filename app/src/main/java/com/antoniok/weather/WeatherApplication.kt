package com.antoniok.weather

import android.app.Application
import com.antoniok.feature.home.di.homeModule
import com.antoniok.weather.data_source.remote.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

private val modules = listOf(
    networkModule,
    homeModule
)

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            koin.loadModules(modules)
        }
    }

}
