package com.example.quizapp.data.repo.quiz

import com.example.quizapp.data.datasource.remote.QuizRemote
import com.example.quizapp.domain.entity.Quiz.Quiz
import com.example.quizapp.domain.repo.quiz.QuizRepository
import com.example.quizapp.ui.state.Result
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val remote: QuizRemote) : QuizRepository {
    override suspend fun getAllQuizzes(): Result<List<Quiz>> {
        return remote.getAllQuizzes()
    }
}