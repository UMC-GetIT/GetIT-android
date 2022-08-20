package com.getit.getit.ui.main.comparison

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemCompareAnswerBinding
import com.getit.getit.databinding.ItemRecommendAnswerBinding
import com.getit.getit.ui.main.comparison.data.CompareAnswer

class CompareResultRVAdapter (private val compareAnswers: ArrayList<CompareAnswer>) : RecyclerView.Adapter<CompareResultRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemCompareAnswerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(answer: CompareAnswer) {
            binding.conditionBox.text = answer.condition;
            binding.compareAnswer1Tv.text = answer.answer1;
            binding.compareAnswer2Tv.text = answer.answer2;
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CompareResultRVAdapter.ViewHolder {
        val binding: ItemCompareAnswerBinding = ItemCompareAnswerBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompareResultRVAdapter.ViewHolder, position: Int) {
        holder.bind(compareAnswers[position])
    }

    override fun getItemCount(): Int = compareAnswers.size
}