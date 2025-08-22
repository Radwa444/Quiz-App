package com.example.quizapp.data.datasource.remote

import android.annotation.SuppressLint
import android.util.Log
import com.example.quizapp.domain.entity.Question.Question
import com.example.quizapp.domain.entity.Quiz.Quiz
import com.example.quizapp.ui.state.Result
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
class QuizRemote(private val firestore: FirebaseFirestore) {

    private  val TAG = "QuizRemote"
    suspend fun getAllQuestions(idQuiz: String): Result<List<Question>>{
       return try {
           val document=firestore.collection("quizzes")
               .document(idQuiz)
               .collection("questions")
               .get()
               .await()
          val questions= document.documents.mapNotNull {
                   documentSnapshot -> documentSnapshot.toObject(Question::class.java).also {question ->
                       Log.d(TAG,question.toString())
           }
           }
          Result.Success(questions)

       }catch (e: Exception){
           Log.e(TAG,e.toString())
           Result.Error(e)
       }

    }
    suspend fun getAllQuizzes(): Result<List<Quiz>>{
        return try {
            val document=firestore.collection("quizzes")
                .get()
                .await()
          val quizzes = document.documents.mapNotNull {
                documentSnapshot -> documentSnapshot.toObject(Quiz::class.java)
            }
            Result.Success(quizzes)
        }catch (e: Exception)
        {
            Log.e(TAG,e.toString())
            Result.Error(e)
        }
    }
    @SuppressLint("SuspiciousIndentation")
    suspend fun getQuizById(idQuiz: String): Result<Quiz?> {
        return try {
          val document=  firestore.collection("quizzes")
                .document(idQuiz)
                .get()
                .await()
            if (document.exists()) {
              val quiz=  document.toObject(Quiz::class.java)
                Result.Success(quiz)
            }
            else {
                Log.e(TAG, "The Quiz that contains this ID $idQuiz does not exist.")
                Result.Success(null)
            }

        }catch (e: Exception){
            Log.e(TAG,e.toString())
            Result.Error(e)
        }
    }

}