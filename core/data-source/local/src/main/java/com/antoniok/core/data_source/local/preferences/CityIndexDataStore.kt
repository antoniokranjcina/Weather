package com.antoniok.core.data_source.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.antoniok.core.data_source.local.preferences.util.MODAL_INDEX_KEY
import com.antoniok.core.data_source.local.preferences.util.PREFERENCES_DATA_STORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_DATA_STORE)

internal class CityIndexDataStore(private val context: Context) : LocalDataStore {

    override suspend fun saveIndex(index: Int) {
        context.dataStore.edit { preferences ->
            preferences[INDEX_KEY] = index
        }
    }

    override val index: Flow<Int>
        get() = context.dataStore.data.map { preferences ->
            preferences[INDEX_KEY] ?: 0
        }

    companion object {
        val INDEX_KEY = intPreferencesKey(MODAL_INDEX_KEY)
    }
}
