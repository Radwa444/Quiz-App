package com.example.DI

import com.example.quizapp.data.datasource.remote.QuizRemote
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providerFireStore(): FirebaseFirestore=
        FirebaseFirestore.getInstance()
    @Provides
    fun providerQuizRemote(firestore: FirebaseFirestore): QuizRemote
    = QuizRemote(firestore)


}