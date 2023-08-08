package com.andreykosarygin.main_domain

interface Interactor {
    suspend fun getPointsFromAccountDataStorage(): Int
}