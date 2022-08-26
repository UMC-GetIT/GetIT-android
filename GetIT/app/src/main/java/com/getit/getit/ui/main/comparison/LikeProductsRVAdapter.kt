package com.getit.getit.ui.main.comparison

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ItemProductImageNameSetBinding
import com.getit.getit.ui.main.comparison.data.ProductImageName

class LikeProductsRVAdapter (private val likeProductsList: ArrayList<ProductImageName>,
                             val activity :Activity,
                             val comparisonFragment: ComparisonFragment) : RecyclerView.Adapter<LikeProductsRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemProductImageNameSetBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductImageName) {
            if(product.image != null)
                Glide.with(activity).load(product.image!!).into(binding.productImageIv)
            binding.productNameTv.text = product.name
            binding.productImageCv.setOnClickListener {
                product.id?.let { it1 ->
                    comparisonFragment.addSelectProducts(binding.productImageIv.drawable,
                        it1
                    )
                }
            }
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