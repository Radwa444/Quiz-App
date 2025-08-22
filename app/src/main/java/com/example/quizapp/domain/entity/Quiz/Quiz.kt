package com.example.quizapp.domain.entity.Quiz

import com.example.quizapp.domain.entity.Question.Question
import com.google.firebase.firestore.DocumentId


data class Quiz(
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String = "",
    val quiz14: String = "",
    val isActive: Boolean = true,
    val questions: List<Question> = emptyList()
)
