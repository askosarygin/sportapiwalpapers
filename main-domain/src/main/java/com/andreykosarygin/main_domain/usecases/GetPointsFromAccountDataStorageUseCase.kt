package com.andreykosarygin.main_domain.usecases

import com.andreykosarygin.main_domain.Repository

class GetPointsFromAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(): Int = repository.getPointsFromAccountDataStorage()
}