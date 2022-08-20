package com.getit.getit.ui.main.category

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ItemSearchBinding

class SearchRVAdapter(val context: Context, val result: CategoryResult) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {

        if (result.products[position].imgUrl == "" || result.products[position].imgUrl == null) {

        }
        else {
            Log.d("image",result.products[position].imgUrl)
            Glide.with(context).load(result.products[position].imgUrl).into(holder.productImg)
        }
        holder.name.text = result.products[position].name
        holder.price.text = result.products[position].price
    }

    override fun getItemCount(): Int {
        Log.d("test-size", result.products.size.toString())
        return result.products.size
    }

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