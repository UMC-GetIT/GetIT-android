package com.getit.getit.ui.main.home.recommend

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemRecommendAnswerBinding

class RecommendAnswerRVAdapter (private val recommendAnswerList: ArrayList<String>, val activity: Activity) : RecyclerView.Adapter<RecommendAnswerRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemRecommendAnswerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(option: String) {
            binding.recommendOptionChip.text = option;
            binding.recommendOptionChip.setOnClickListener {
                Log.d("테" + binding.recommendOptionChip.text.toString(), binding.recommendOptionChip.isChecked.toString())
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