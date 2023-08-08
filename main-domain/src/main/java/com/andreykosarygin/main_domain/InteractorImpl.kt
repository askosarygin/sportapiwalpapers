package com.andreykosarygin.main_domain

import com.andreykosarygin.main_domain.usecases.GetPointsFromAccountDataStorageUseCase

class InteractorImpl(
    private val getPointsFromAccountDataStorageUseCase: GetPointsFromAccountDataStorageUseCase
) : Interactor {

    override suspend fun getPointsFromAccountDataStorage(): Int =
        getPointsFromAccountDataStorageUseCase.execute()
}