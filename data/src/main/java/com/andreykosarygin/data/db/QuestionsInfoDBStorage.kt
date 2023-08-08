package com.andreykosarygin.data.db

import com.andreykosarygin.common.Difficult
import com.andreykosarygin.common.QuestionInfoDB

interface QuestionsInfoDBStorage {

    suspend fun save(questionInfoDB: QuestionInfoDB): Boolean

    suspend fun getAllWithDifficult(difficult: Difficult): List<QuestionInfoDB>
}