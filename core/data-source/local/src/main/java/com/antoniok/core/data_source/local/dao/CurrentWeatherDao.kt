package com.antoniok.core.data_source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.antoniok.core.data_source.local.entity.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CurrentWeatherDao {

    @Insert
    suspend fun insertCurrentWeather(currentWeather: CurrentWeatherEntity)

    @Query("SELECT * FROM current_weather")
    fun getCurrentWeather(): Flow<CurrentWeatherEntity>

    @Update
    suspend fun updateCurrentWeather(currentWeather: CurrentWeatherEntity)

    @Delete
    suspend fun deleteCurrentWeather(currentWeather: CurrentWeatherEntity)

}
