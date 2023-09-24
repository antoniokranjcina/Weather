package com.antoniok.core.data_source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.antoniok.core.data_source.local.entity.WeatherMetricsEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WeatherMetricsDao {

    @Insert
    suspend fun insertWeatherMetrics(weatherMetrics: WeatherMetricsEntity)

    @Query("SELECT * FROM weather_metrics")
    fun getWeatherMetrics(): Flow<WeatherMetricsEntity>

    @Update
    suspend fun updateWeatherMetrics(weatherMetrics: WeatherMetricsEntity)

    @Delete
    suspend fun deleteWeatherMetrics(weatherMetrics: WeatherMetricsEntity)

}
