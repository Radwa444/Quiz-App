package com.example.quizapp.ui.quizzes.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.utils.ID_QUIZ
import com.example.quizapp.databinding.ActivityQuizBinding
import com.example.quizapp.domain.entity.Quiz.Quiz

import com.example.quizapp.utils.name_input
import com.example.quizapp.ui.questions.activity.QuestionActivity
import com.example.quizapp.ui.quizzes.adapter.QuizAdapter
import com.example.quizapp.ui.quizzes.viewmodel.QuizViewModel
import com.example.quizapp.ui.state.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {
    private  val TAG = "QuizActivity"
    lateinit var binding: ActivityQuizBinding
   private val viewModel: QuizViewModel by viewModels()

    lateinit var adapter: QuizAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getAllQuizzes()
        setTitletext()
        observerStateFlow()





    }

    private fun observerStateFlow() {
        lifecycleScope.launch {
            viewModel.quizStateFlow.collect {
                value ->
                when(value){
                    is Result.Success<*> -> {
                      adapter= QuizAdapter(value.data as List<Quiz>)
                        setRecycleView()
                       // progressBar.visibility= View.GONE
                        binding.loadingInQuiz.root.visibility=View.GONE

                    }
                    is Result.Error<*> -> {
                        Toast.makeText(this@QuizActivity, "please enter name ", Toast.LENGTH_SHORT)
                            .show()
                        //progressBar.visibility= View.VISIBLE
                        binding.loadingInQuiz.root.visibility=View.GONE
                    }
                    is Result.Loading -> {
                        //progressBar.visibility= View.VISIBLE
                        binding.loadingInQuiz.root.visibility=View.VISIBLE
                    }

                }
            }

        }
    }

    private fun setRecycleView() {
        val linearLayoutManager= LinearLayoutManager(this)
        binding.recQuiz.layoutManager=linearLayoutManager
        binding.recQuiz.adapter=adapter
        adapter.setOnClickListener(
           object : QuizAdapter.OnClickListener{
               override fun Listener(
                   position: Int,
                   quiz: Quiz
               ) {
                   val intent= Intent(this@QuizActivity, QuestionActivity::class.java)
                   intent.putExtra(ID_QUIZ,quiz.id)
                   startActivity(intent)
               }

           }
        )

        }





private fun setTitletext() {
    try {
        val name = intent.getStringExtra(name_input)
        binding.textView.text = "Hi, $name"
    } catch (e: Exception) {
        Log.e(TAG, e.toString())
    }

}
}