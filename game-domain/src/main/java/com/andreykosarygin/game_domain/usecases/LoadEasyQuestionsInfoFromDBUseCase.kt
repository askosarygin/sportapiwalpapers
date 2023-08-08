package com.andreykosarygin.game_domain.usecases

import com.andreykosarygin.common.Difficult
import com.andreykosarygin.common.QuestionInfo
import com.andreykosarygin.game_domain.Repository

class LoadEasyQuestionsInfoFromDBUseCase(
    private val repository: Repository
) {
    suspend fun execute(): List<QuestionInfo> = repository
        .loadQuestionsInfoDBWithDifficult(Difficult.Easy)
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