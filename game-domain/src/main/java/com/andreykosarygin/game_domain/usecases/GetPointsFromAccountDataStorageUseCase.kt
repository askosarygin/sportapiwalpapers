package com.andreykosarygin.game_domain.usecases

import com.andreykosarygin.game_domain.Repository

class GetPointsFromAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(): Int = repository.getPointsFromAccountDataStorage()
}