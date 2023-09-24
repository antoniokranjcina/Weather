package com.antoniok.core.data_source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.antoniok.core.data_source.local.entity.DailyWeatherForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface DailyWeatherForecastDao {

    @Insert
    suspend fun insertDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity)

    @Query("SELECT * FROM daily_weather_forecast")
    fun getDailyWeatherForecast(): Flow<DailyWeatherForecastEntity>

    @Update
    suspend fun updateDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity)

    @Delete
    suspend fun deleteDailyWeatherForecast(dailyWeather: DailyWeatherForecastEntity)

}
