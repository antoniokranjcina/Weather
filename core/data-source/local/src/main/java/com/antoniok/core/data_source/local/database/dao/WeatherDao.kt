package com.antoniok.core.data_source.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.antoniok.core.data_source.local.database.entity.WeatherEntity
import com.antoniok.core.data_source.local.database.entity.WeatherWithDaysAndHours
import com.antoniok.core.data_source.local.database.entity.forecast.ForecastDayEntity
import com.antoniok.core.data_source.local.database.entity.forecast.HourEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHours(hours: List<HourEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertForecastDays(forecastDays: List<ForecastDayEntity>)

    @Transaction
    @Query("SELECT * FROM weather_entity WHERE name = :city")
    fun getWeatherByCity(city: String): Flow<WeatherWithDaysAndHours?>

    @Transaction
    @Query("SELECT * FROM weather_entity ORDER BY id DESC")
    fun getAllWeathers(): Flow<List<WeatherWithDaysAndHours>>

    @Query("SELECT name FROM weather_entity")
    fun getCities(): Flow<List<String>>

    @Query("DELETE FROM weather_entity")
    suspend fun deleteAllWeathers(): Int

    @Delete
    suspend fun deleteWeather(weather: WeatherEntity)

}
