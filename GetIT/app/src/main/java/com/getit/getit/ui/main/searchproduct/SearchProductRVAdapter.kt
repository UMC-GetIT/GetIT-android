package com.getit.getit.ui.main.searchproduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ItemSearchBinding
import java.text.DecimalFormat

class SearchProductRVAdapter(val context: Context, val result: List<SearchProductResult>) : RecyclerView.Adapter<SearchProductRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchProductRVAdapter.ViewHolder {
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchProductRVAdapter.ViewHolder, position: Int) {

        if (result[position].image == "" || result[position].image == null) {

        }
        else {
            Glide.with(context).load(result[position].image).into(holder.productImg)
        }
        holder.name.text = result[position].title

        val price = result[position].lprice
        val df = DecimalFormat("###,###")
        val strPrice = df.format(price) + " 원"
        holder.price.text = strPrice
    }

    override fun getItemCount(): Int {
        Log.d("test-size", result.size.toString())
        return result.size
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