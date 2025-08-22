package com.example.quizapp.ui.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.utils.SCOPE
import com.example.quizapp.databinding.ActivityResultBinding
import com.example.quizapp.ui.quizzes.activity.QuizActivity

class ResultActivity : AppCompatActivity() {
lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score =intent.getStringExtra(SCOPE)
        binding.textScore.text=score
        binding.buttonFinish.setOnClickListener {
            val intent= Intent(this@ResultActivity, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}