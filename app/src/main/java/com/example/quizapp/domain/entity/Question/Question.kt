package com.example.quizapp.domain.entity.Question

import com.google.firebase.firestore.DocumentId

data class Question(
     val id: String = "",
    val text: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: Int = 0
)