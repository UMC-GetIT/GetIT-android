package com.getit.getit.ui.main.category

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.databinding.ItemSearchBinding
import com.getit.getit.ui.main.category.detail.ProductDetailActivity
import java.text.DecimalFormat

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
        var price = result.products[position].lprice
        val df = DecimalFormat("###,###")
        price = df.format(price.toInt()).toString() + " 원"
        holder.price.text = price

        holder.binding.itemProduct.setOnClickListener{
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("productId", result.products[position].productId)
            intent.putExtra("productName", result.products[position].name)
            intent.putExtra("price", price)
            intent.putExtra("imageUrl", result.products[position].imgUrl)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int =  result.products.size

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