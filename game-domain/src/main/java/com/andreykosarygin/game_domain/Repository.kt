package com.andreykosarygin.game_domain

import com.andreykosarygin.common.Difficult
import com.andreykosarygin.common.QuestionInfoDB

interface Repository {
    suspend fun loadQuestionsInfoDBWithDifficult(difficult: Difficult): List<QuestionInfoDB>

    suspend fun getPointsFromAccountDataStorage(): Int

    suspend fun savePointsToAccountDataStorage(points: Int): Boolean
}