package com.example.quizapp.DI

import com.example.quizapp.data.datasource.remote.QuizRemote
import com.example.quizapp.data.repo.question.QuestionRepositoryImpl
import com.example.quizapp.data.repo.quiz.QuizRepositoryImpl
import com.example.quizapp.domain.repo.question.QuestionRepository
import com.example.quizapp.domain.repo.quiz.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    fun providerQuizRepo(remote: QuizRemote): QuizRepository=
        QuizRepositoryImpl(remote)
    @Provides
    fun providerQuestionRepo(remote: QuizRemote): QuestionRepository=
        QuestionRepositoryImpl(remote)
}