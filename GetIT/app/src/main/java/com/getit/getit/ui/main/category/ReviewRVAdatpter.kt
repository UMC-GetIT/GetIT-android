package com.getit.getit.ui.main.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemProductReviewBinding

class ReviewRVAdatpter(private val reviewList: ArrayList<Reviews>) : RecyclerView.Adapter<ReviewRVAdatpter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemProductReviewBinding = ItemProductReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // * position: index id
        holder.bind(reviewList[position])
    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(val binding: ItemProductReviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Reviews) {
            binding.itemProductReviewUserNameTv.text = review.userName
            binding.itemProductReviewReviewContentTv.text = review.content
            binding.itemReviewImgIv.setImageResource(review.coverImg!!)
        }
    }
}