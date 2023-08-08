package com.andreykosarygin.game_domain.usecases

import com.andreykosarygin.common.Difficult
import com.andreykosarygin.common.QuestionInfo
import com.andreykosarygin.game_domain.Repository

class LoadHardQuestionsInfoFromDBUseCase(
    private val repository: Repository
) {
    suspend fun execute(): List<QuestionInfo> = repository
        .loadQuestionsInfoDBWithDifficult(Difficult.Hard)
        .map {
            QuestionInfo(
                it.questionText,
                it.correctAnswer,
                it.incorrectAnswerOne,
                it.incorrectAnswerTwo,
                it.incorrectAnswerThree,
            )
        }
}