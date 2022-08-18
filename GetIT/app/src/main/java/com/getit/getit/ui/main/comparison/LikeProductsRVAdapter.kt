package com.getit.getit.ui.main.comparison

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemProductImageNameSetBinding
import com.getit.getit.ui.main.comparison.data.ProductImageName

class LikeProductsRVAdapter (private val likeProductsList: ArrayList<ProductImageName>) : RecyclerView.Adapter<LikeProductsRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemProductImageNameSetBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductImageName) {
            binding.productImageIv.setImageResource(product.image!!)
            binding.productNameTv.text = product.name
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LikeProductsRVAdapter.ViewHolder {
        val binding: ItemProductImageNameSetBinding = ItemProductImageNameSetBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikeProductsRVAdapter.ViewHolder, position: Int) {
        holder.bind(likeProductsList[position])
    }

    override fun getItemCount(): Int = likeProductsList.size
}