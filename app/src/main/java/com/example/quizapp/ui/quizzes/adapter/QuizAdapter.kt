package com.example.quizapp.ui.quizzes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.QuizItemBinding
import com.example.quizapp.domain.entity.Quiz.Quiz

class QuizAdapter (private val quizzes:List<Quiz>): RecyclerView.Adapter<QuizAdapter.QuizAdapterViewHolder>(){
  private var onClickListener: OnClickListener?=null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizAdapterViewHolder {
        val binding= QuizItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuizAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: QuizAdapterViewHolder,
        position: Int
    ) {
        val quiz=quizzes.get(position)
        holder.binding.textTitleQuiz.text=quiz.title
        holder.binding.textDes.text=quiz.description
        holder.itemView.setOnClickListener {
            onClickListener?.Listener(position,quiz)

        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener=onClickListener
    }
    interface OnClickListener{
        fun Listener(position: Int,quiz: Quiz)
    }

    class QuizAdapterViewHolder( val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}