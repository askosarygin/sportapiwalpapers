package com.andreykosarygin.wallpapers_domain.usecases

import com.andreykosarygin.wallpapers_domain.Repository

class SavePointsToAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(points: Int): Boolean = repository.savePointsToAccountDataStorage(points)
}