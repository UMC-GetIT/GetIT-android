package com.getit.getit.ui.main.category.detail.review

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemProductReviewBinding

class ReviewRVAdatpter(val context: Context, val result: List<ReviewResult>) : RecyclerView.Adapter<ReviewRVAdatpter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ReviewRVAdatpter.ViewHolder {
        val binding: ItemProductReviewBinding = ItemProductReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = result[position].nickName
        holder.userReview.text = result[position].review
    }

    override fun getItemCount(): Int = result.size

    inner class ViewHolder(val binding: ItemProductReviewBinding): RecyclerView.ViewHolder(binding.root) {
        val userName = binding.itemProductReviewUserNameTv
        val userReview = binding.itemProductReviewReviewContentTv
    }
}