package com.example.quizapp.ui.questions.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels


import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.quizapp.ID_QUIZ
import com.example.quizapp.R
import com.example.quizapp.SCOPE
import com.example.quizapp.databinding.ActivityQuestionBinding
import com.example.quizapp.domain.entity.Question.Question
import com.example.quizapp.ui.questions.viewmodel.QuestionViewModel
import com.example.quizapp.ui.result.ResultActivity
import com.example.quizapp.ui.state.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class QuestionActivity : AppCompatActivity() {
    private val TAG = "QuestionActivity"
    lateinit var binding: ActivityQuestionBinding
    lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score=0
    val viewModel: QuestionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(ID_QUIZ)
        Log.d(TAG, id.toString())
        viewModel.getAllQuestionsByIdQuiz(id.toString())
        observerQuestionStateFlow()
        setupClickListeners()


    }

    private fun showEvaluation() {
        binding.textView7.text="${questions.size} / $score"
        binding.progressBar2.max=questions.size
        binding.progressBar2.progress = currentQuestionIndex

    }

    private fun setupClickListeners() {
        binding.option1.setOnClickListener { checkAnswer(0) }
        binding.option2.setOnClickListener { checkAnswer(1) }
        binding.option3.setOnClickListener { checkAnswer(2) }
        binding.option4.setOnClickListener { checkAnswer(3) }
        binding.buttonCheck.setOnClickListener {
            nextQuestion()
        }
    }

    private fun observerQuestionStateFlow() {
        lifecycleScope.launch {
            viewModel.QuestionsStateFlow.collect { result ->
                when (result) {
                    is Result.Error<*> -> {
                        binding.loadingInQuestion.root.visibility= View.GONE
                        Toast.makeText(
                            this@QuestionActivity, result.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Result.Loading -> {
                        binding.loadingInQuestion.root.visibility= View.VISIBLE
                    }
                    is Result.Success<*> -> {
                        binding.loadingInQuestion.root.visibility= View.GONE
                        questions = result.data as List<Question>
                        Log.d(TAG, questions.get(0).id)
                        showQuestions(0)

                    }
                }
            }
        }
    }

    private fun showQuestions(index: Int) {
        showEvaluation()

        if(index < questions.size){
            binding.textQuestion.text=questions[index].text
            binding.option1.text=questions[index].options[0]
            binding.option2.text=questions[index].options[1]
            binding.option3.text=questions[index].options[2]
            binding.option4.text=questions[index].options[3]



            resetOptionBackground()
        }
        else {
           val intent= Intent(this@QuestionActivity, ResultActivity::class.java)
            intent.putExtra(SCOPE,"${questions.size} / $score")
            startActivity(intent)
            finish()

        }
    }

    private fun resetOptionBackground() {
       val options= listOf(binding.option1,
           binding.option2,
           binding.option3,
           binding.option4)
        options.forEach {button->
            button.background= ContextCompat.getDrawable(this@QuestionActivity, R.drawable.containor_options)

        }

    }
    private fun checkAnswer(selectedAnswer: Int ) {
        if (selectedAnswer==questions[currentQuestionIndex].correctAnswer) score++
        highlightAnswers(selectedAnswer,questions[currentQuestionIndex].correctAnswer)
        disableOptions()

    }

    private fun highlightAnswers(selectedAnswer: Int, correctAnswer: Int) {
        val options= listOf(binding.option1,binding.option2,binding.option3,binding.option4)
        options.forEachIndexed {
            index, view ->
           when{
               index==correctAnswer -> {
                   view.background =
                       ContextCompat.getDrawable(
                           this@QuestionActivity,
                           R.drawable.backgroud_correct_answer

                       )

               }

               selectedAnswer==index && index!=correctAnswer ->view.background=
                   ContextCompat.getDrawable(this@QuestionActivity,R.drawable.background_answer_is_wrong)

           }
        }
    }
    private fun disableOptions() {
        val options = listOf(binding.option1, binding.option2, binding.option3, binding.option4)
        options.forEach { it.isEnabled = false }
    }

    private fun enableOptions() {
        val options = listOf(binding.option1, binding.option2, binding.option3, binding.option4)
        options.forEach { it.isEnabled = true }
    }
    private fun QuestionActivity.nextQuestion() {
        currentQuestionIndex++
        enableOptions()
        resetOptionBackground()
        showQuestions(currentQuestionIndex)

    }

}




