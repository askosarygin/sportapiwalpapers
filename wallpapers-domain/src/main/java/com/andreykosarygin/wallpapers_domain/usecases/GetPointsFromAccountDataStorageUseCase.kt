package com.andreykosarygin.wallpapers_domain.usecases

import com.andreykosarygin.wallpapers_domain.Repository

class GetPointsFromAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(): Int = repository.getPointsFromAccountDataStorage()
}