package com.andreykosarygin.game_domain.usecases

import com.andreykosarygin.game_domain.Repository

class SavePointsToAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(points: Int): Boolean = repository.savePointsToAccountDataStorage(points)
}