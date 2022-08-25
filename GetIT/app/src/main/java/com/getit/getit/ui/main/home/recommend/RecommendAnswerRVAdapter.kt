package com.getit.getit.ui.main.home.recommend

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemRecommendAnswerBinding
import com.google.android.material.chip.Chip

class RecommendAnswerRVAdapter (
    private val recommendAnswerList: ArrayList<String>,
    val activity: Activity,
    val questionNum:Int,
    val recommendQuestionRVAdapter: RecommendQuestionRVAdapter) : RecyclerView.Adapter<RecommendAnswerRVAdapter.ViewHolder>() {

    var chipList : MutableList<Chip> = mutableListOf()

    inner class ViewHolder(val binding: ItemRecommendAnswerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(option: String) {
            binding.recommendOptionChip.text = option;
            binding.recommendOptionChip.setOnClickListener {
                Log.d("테" + binding.recommendOptionChip.text.toString(), binding.recommendOptionChip.isChecked.toString())
                for(c in chipList){
                    c.isChecked = false;
                }
                binding.recommendOptionChip.isChecked = true
                recommendQuestionRVAdapter.updateAnswerList(questionNum, binding.recommendOptionChip.text.toString())
            }
            if(!chipList.contains(binding.recommendOptionChip)) {
                chipList.add(binding.recommendOptionChip)
            }
            if(chipList.size == 1) {
                chipList[0].isChecked = true
                recommendQuestionRVAdapter.updateAnswerList(questionNum, chipList[0].text.toString())
            }

        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecommendAnswerRVAdapter.ViewHolder {
        val binding: ItemRecommendAnswerBinding = ItemRecommendAnswerBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendAnswerRVAdapter.ViewHolder, position: Int) {
        holder.bind(recommendAnswerList[position])
    }

    override fun getItemCount(): Int = recommendAnswerList.size
}