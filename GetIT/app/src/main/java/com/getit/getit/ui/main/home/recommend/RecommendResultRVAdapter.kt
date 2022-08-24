package com.getit.getit.ui.main.home.recommend

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.data.NameImagePriceProduct
import com.getit.getit.databinding.ItemRecommendProductBinding

class RecommendResultRVAdapter (private val recommendProductsList: ArrayList<NameImagePriceProduct>, activity: Activity) : RecyclerView.Adapter<RecommendResultRVAdapter.ViewHolder>() {

    val activity = activity

    inner class ViewHolder(val binding: ItemRecommendProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: NameImagePriceProduct) {
            //Glide.with(activity).load(product.coverImg).into(binding.recommendProductIv)
            product.coverImg?.let { binding.recommendProductIv.setImageResource(it) }
            binding.recommendProductNameTv.text = product.name
            binding.recommendProductPriceTv.text = product.price
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecommendResultRVAdapter.ViewHolder {
        val binding: ItemRecommendProductBinding = ItemRecommendProductBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendResultRVAdapter.ViewHolder, position: Int) {
        holder.bind(recommendProductsList[position])
    }

    override fun getItemCount(): Int = recommendProductsList.size
}