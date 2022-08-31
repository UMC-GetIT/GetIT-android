package com.getit.getit.ui.main.category.detail.review

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ItemProductReviewBinding

class ReviewListRVAdatpter(val context: Context, val result: List<ReviewListResult>) : RecyclerView.Adapter<ReviewListRVAdatpter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ReviewListRVAdatpter.ViewHolder {
        val binding: ItemProductReviewBinding = ItemProductReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = result[position].nickName
        holder.userReview.text = result[position].review
        if (result[position].profileImgUrl == "" || result[position].profileImgUrl == null) { }
        else {
            Glide.with(context).load(result[position]).into(holder.image)
        }
    }

    override fun getItemCount(): Int = result.size

    inner class ViewHolder(val binding: ItemProductReviewBinding): RecyclerView.ViewHolder(binding.root) {
        val userName = binding.itemProductReviewUserNameTv
        val userReview = binding.itemProductReviewReviewContentTv
        val image = binding.itemProductReviewUserImgIv
    }
}