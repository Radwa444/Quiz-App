package com.example.quizapp.ui.main.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding

import com.example.quizapp.utils.name_input
import com.example.quizapp.ui.quizzes.activity.QuizActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {

            val name = binding.editName.text?.toString()?.trim()
            Log.d("MainActivity", "Name: $name")

            if (!name.isNullOrEmpty()){
                val intent= Intent(this@MainActivity, QuizActivity::class.java)
                intent.putExtra(name_input,name)
                startActivity(intent)

            }else{
                Toast.makeText(this@MainActivity,"please enter name ", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
