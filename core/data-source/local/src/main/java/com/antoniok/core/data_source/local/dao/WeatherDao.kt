package com.antoniok.core.data_source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniok.core.data_source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM WeatherEntity WHERE name = :city")
    fun getWeatherByCity(city: String): Flow<WeatherEntity?>

    @Query("SELECT * FROM WeatherEntity")
    fun getAllWeathers(): Flow<List<WeatherEntity>>

    @Query("DELETE FROM WeatherEntity")
    suspend fun deleteAllWeathers(): Int

    @Delete
    suspend fun deleteWeather(weather: WeatherEntity)

}
