package com.antoniok.weather

import android.app.Application
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.antoniok.core.data.di.dataModule
import com.antoniok.core.data_source.local.di.localModule
import com.antoniok.feature.home.di.homeModule
import com.antoniok.weather.data_source.remote.di.networkModule
import com.antoniok.weather.di.appModule
import com.antoniok.weather.sync.SyncWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

private val modules = listOf(
    appModule,
    networkModule,
    localModule,
    dataModule,
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

        val workRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java).build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }

}
