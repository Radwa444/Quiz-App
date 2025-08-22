package com.example.quizapp.domain.repo.quiz

import com.example.quizapp.domain.entity.Quiz.Quiz
import com.example.quizapp.ui.state.Result

interface QuizRepository {
    suspend fun getAllQuizzes(): Result<List<Quiz>>
}