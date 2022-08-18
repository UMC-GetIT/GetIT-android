package com.getit.getit.ui.main.home.recommend

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.getit.getit.databinding.ItemRecommendQuestionBinding
import com.getit.getit.ui.main.home.data.RecommendQuestion
import java.security.AccessController.getContext

class RecommendQuestionRVAdapter(
    private val recommendQuestionList: ArrayList<RecommendQuestion>,
    val activity: RecommendQuestionActivity
) : RecyclerView.Adapter<RecommendQuestionRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemRecommendQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: RecommendQuestion) {
            binding.recommendQuestionTv.text = question.question
            var layoutManager: RecyclerView.LayoutManager
            //val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            layoutManager = ChipsLayoutManager.newBuilder(activity).build()
            binding.recommendAnswerRv.layoutManager = layoutManager

            binding.recommendAnswerRv.adapter =
                question.options?.let { RecommendAnswerRVAdapter(it) }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecommendQuestionRVAdapter.ViewHolder {
        val binding: ItemRecommendQuestionBinding = ItemRecommendQuestionBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendQuestionRVAdapter.ViewHolder, position: Int) {
        holder.bind(recommendQuestionList[position])
    }

    override fun getItemCount(): Int = recommendQuestionList.size

}