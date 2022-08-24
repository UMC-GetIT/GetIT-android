package com.getit.getit.ui.main.home.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.getit.getit.databinding.ItemRecommendQuestionBinding
import com.getit.getit.ui.main.home.data.RecommendQuestion

class RecommendQuestionRVAdapter(
    private val recommendQuestionList: ArrayList<RecommendQuestion>,
    val activity: RecommendQuestionActivity
) : RecyclerView.Adapter<RecommendQuestionRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemRecommendQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: RecommendQuestion) {
            binding.recommendQuestionTv.text = question.question
            var layoutManager: RecyclerView.LayoutManager
            layoutManager = ChipsLayoutManager.newBuilder(activity)
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .build()

            binding.recommendAnswerRv.layoutManager = layoutManager

            var recommendAnswerAdapter = question.options?.let { RecommendAnswerRVAdapter(it) }
            binding.recommendAnswerRv.adapter  = recommendAnswerAdapter

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