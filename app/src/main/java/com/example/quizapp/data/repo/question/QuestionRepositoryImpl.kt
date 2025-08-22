package com.example.quizapp.data.repo.question

import com.example.quizapp.data.datasource.remote.QuizRemote
import com.example.quizapp.domain.entity.Question.Question
import com.example.quizapp.domain.repo.question.QuestionRepository
import com.example.quizapp.ui.state.Result
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(private val remote: QuizRemote) : QuestionRepository {
    override suspend fun getQuestion(idQuiz:String): Result<List<Question>> {
        return remote.getAllQuestions(idQuiz)
    }
}