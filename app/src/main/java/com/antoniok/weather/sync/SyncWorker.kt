package com.antoniok.weather.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.antoniok.core.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val DEFAULT_CITY = "Zagreb"
private const val DEFAULT_DAYS = 7

class SyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    private val weatherRepository: WeatherRepository by inject()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val result = weatherRepository.sync(city = DEFAULT_CITY, days = DEFAULT_DAYS)
            if (result) {
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
