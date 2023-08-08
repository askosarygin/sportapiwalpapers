package com.andreykosarygin.main_domain

interface Repository {
    suspend fun getPointsFromAccountDataStorage(): Int
}