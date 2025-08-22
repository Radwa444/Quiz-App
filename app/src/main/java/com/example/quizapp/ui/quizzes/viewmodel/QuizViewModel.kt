package com.example.quizapp.ui.quizzes.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.entity.Quiz.Quiz
import com.example.quizapp.domain.repo.quiz.QuizRepository
import com.example.quizapp.ui.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val quizRepository: QuizRepository): ViewModel() {
    private  val TAG = "QuizViewModel"
    private val _quizStateFlow: MutableStateFlow<Result<List<Quiz>>> =
        MutableStateFlow(Result.Loading)
    val quizStateFlow: StateFlow<Result<List<Quiz>>> =_quizStateFlow
  init {
      getAllQuizzes()
  }
    fun getAllQuizzes(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _quizStateFlow.value= Result.Loading
                _quizStateFlow.value=quizRepository.getAllQuizzes()
                Log.e(TAG,quizRepository.getAllQuizzes().toString())
            }catch (e: Exception){
                _quizStateFlow.value= Result.Error(e)
                Log.e(TAG,e.toString())
            }


        }
    }
    fun onRefresh(){
      getAllQuizzes()
    }
}