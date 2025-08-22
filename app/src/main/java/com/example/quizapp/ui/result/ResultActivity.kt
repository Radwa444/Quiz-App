package com.example.quizapp.ui.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.SCOPE
import com.example.quizapp.databinding.ActivityResultBinding
import com.example.quizapp.name_input
import com.example.quizapp.ui.quizzes.activity.QuizActivity
import java.util.jar.Attributes

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