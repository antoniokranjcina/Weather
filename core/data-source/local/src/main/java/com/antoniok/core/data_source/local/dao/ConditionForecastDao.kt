package com.antoniok.core.data_source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.antoniok.core.data_source.local.entity.ConditionForecastEntity
import com.antoniok.core.data_source.local.entity.HourInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ConditionForecastDao {

    @Insert
    suspend fun insertConditionForecast(conditionForecast: ConditionForecastEntity)

    @Insert
    suspend fun insertHourInfo(hoursInfo: List<HourInfoEntity>)

    @Query("SELECT * FROM condition_forecast")
    fun getConditionForecasts(): Flow<ConditionForecastEntity>

    @Query("SELECT * FROM hour_info")
    fun getHoursInfo(): Flow<List<HourInfoEntity>>

    @Update
    suspend fun updateConditionForecast(conditionForecast: ConditionForecastEntity)

    @Update
    suspend fun updateHours(hoursInfo: List<HourInfoEntity>)

    @Delete
    suspend fun deleteConditionForecast(conditionForecast: ConditionForecastEntity)

    @Delete
    suspend fun deleteHour(hoursInfo: HourInfoEntity)

}
