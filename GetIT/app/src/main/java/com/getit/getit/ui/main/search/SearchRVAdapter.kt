package com.getit.getit.ui.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.CategoryResult
import com.getit.getit.databinding.ItemSearchBinding
import java.text.DecimalFormat

class SearchRVAdapter(val context: Context, val result: List<CategoryResult>) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {

        if (result[position].imgUrl == "" || result[position].imgUrl == null) {

        }
        else {
            Glide.with(context).load(result[position].imgUrl).into(holder.productImg)
        }
        holder.name.text = result[position].name
        holder.price.text = result[position].price
    }

    override fun getItemCount(): Int = result.size

    inner class ViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {

        val productImg: ImageView = binding.itemSearchImgIv
        val name: TextView = binding.itemSearchProductNameTv
        val price: TextView = binding.itemSearchProductPriceTv
    }

    interface ProductClickListener {
        fun onProductClick(productId: Int)
    }
    private lateinit var productClickListener: ProductClickListener

    fun setProductClickListener(itemClickListener: ProductClickListener) {
        productClickListener = itemClickListener
    }
}