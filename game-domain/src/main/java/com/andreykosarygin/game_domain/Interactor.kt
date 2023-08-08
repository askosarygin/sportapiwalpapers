package com.andreykosarygin.game_domain

import com.andreykosarygin.common.QuestionInfo

interface Interactor {
    suspend fun loadEasyQuestionsInfoFromDB(): List<QuestionInfo>

    suspend fun loadNormalQuestionsInfoFromDB(): List<QuestionInfo>

    suspend fun loadHardQuestionsInfoFromDB(): List<QuestionInfo>

    suspend fun getPointsFromAccountDataStorage(): Int

    suspend fun savePointsToAccountDataStorage(points: Int): Boolean
}