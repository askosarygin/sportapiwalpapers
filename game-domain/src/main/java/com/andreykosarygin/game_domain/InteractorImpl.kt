package com.andreykosarygin.game_domain

import com.andreykosarygin.common.QuestionInfo
import com.andreykosarygin.game_domain.usecases.GetPointsFromAccountDataStorageUseCase
import com.andreykosarygin.game_domain.usecases.LoadEasyQuestionsInfoFromDBUseCase
import com.andreykosarygin.game_domain.usecases.LoadHardQuestionsInfoFromDBUseCase
import com.andreykosarygin.game_domain.usecases.LoadNormalQuestionsInfoFromDBUseCase
import com.andreykosarygin.game_domain.usecases.SavePointsToAccountDataStorageUseCase

class InteractorImpl(
    private val loadEasyQuestionsInfoFromDBUseCase: LoadEasyQuestionsInfoFromDBUseCase,
    private val loadNormalQuestionsInfoFromDBUseCase: LoadNormalQuestionsInfoFromDBUseCase,
    private val loadHardQuestionsInfoFromDBUseCase: LoadHardQuestionsInfoFromDBUseCase,
    private val getPointsFromAccountDataStorageUseCase: GetPointsFromAccountDataStorageUseCase,
    private val savePointsToAccountDataStorageUseCase: SavePointsToAccountDataStorageUseCase
) : Interactor {

    override suspend fun loadEasyQuestionsInfoFromDB(): List<QuestionInfo> =
        loadEasyQuestionsInfoFromDBUseCase.execute()

    override suspend fun loadNormalQuestionsInfoFromDB(): List<QuestionInfo> =
        loadNormalQuestionsInfoFromDBUseCase.execute()

    override suspend fun loadHardQuestionsInfoFromDB(): List<QuestionInfo> =
        loadHardQuestionsInfoFromDBUseCase.execute()

    override suspend fun getPointsFromAccountDataStorage(): Int =
        getPointsFromAccountDataStorageUseCase.execute()

    override suspend fun savePointsToAccountDataStorage(points: Int): Boolean =
        savePointsToAccountDataStorageUseCase.execute(points)
}