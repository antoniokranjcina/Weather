package com.antoniok.core.data

interface Sync {

    suspend fun sync(city: String, days: Int): Boolean

}
