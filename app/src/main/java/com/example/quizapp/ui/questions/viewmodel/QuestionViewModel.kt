package com.example.quizapp.ui.questions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.entity.Question.Question
import com.example.quizapp.domain.repo.question.QuestionRepository
import com.example.quizapp.ui.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository): ViewModel() {
    private val _QuestionsStateFlow: MutableStateFlow<Result<List<Question>>> = MutableStateFlow(
        Result.Loading)
    val QuestionsStateFlow: StateFlow<Result<List<Question>>> = _QuestionsStateFlow

    fun getAllQuestionsByIdQuiz(idQuiz: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _QuestionsStateFlow.value=Result.Loading
                _QuestionsStateFlow.value=repository.getQuestion(idQuiz)
            }catch (e: Exception){
                _QuestionsStateFlow.value= Result.Error(e)
            }
        }



    }
}