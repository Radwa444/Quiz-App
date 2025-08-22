package com.example.quizapp.domain.repo.question

import com.example.quizapp.domain.entity.Question.Question
import com.example.quizapp.ui.state.Result

interface QuestionRepository {
    suspend fun getQuestion(idQuiz:String): Result<List<Question>>
}