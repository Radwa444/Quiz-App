package com.example.quizapp.ui.state

sealed interface Result<out T> {
    data class  Success<T>(val data:T): Result<T>
    data class Error<T>(val error: Exception): Result<T>
    object Loading: Result<Nothing>
}