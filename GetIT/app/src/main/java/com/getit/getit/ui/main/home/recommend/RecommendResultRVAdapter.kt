package com.getit.getit.ui.main.home.recommend

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.data.NameImagePriceProduct
import com.getit.getit.databinding.ItemRecommendProductBinding
import com.getit.getit.ui.main.category.detail.ProductDetailActivity
import java.text.DecimalFormat

class RecommendResultRVAdapter (val context: Context, private val recommendProductsList: ArrayList<NameImagePriceProduct>, activity: Activity) : RecyclerView.Adapter<RecommendResultRVAdapter.ViewHolder>() {

    val activity = activity

    inner class ViewHolder(val binding: ItemRecommendProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: NameImagePriceProduct) {
            Glide.with(activity).load(product.coverImg).into(binding.recommendProductIv)
            binding.recommendProductNameTv.text = product.name
            var price = product.price
            binding.recommendProductPriceTv.text = changePriceFormat(price)
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
        var price = recommendProductsList[position].price
        price = changePriceFormat(price)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("productId", recommendProductsList[position].id)
            intent.putExtra("productName", recommendProductsList[position].name)
            intent.putExtra("price", price)
            intent.putExtra("imageUrl", recommendProductsList[position].coverImg)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    private fun changePriceFormat(price: String?): String? {
        var res = price
        res = res!!.replace("원", "")
        val df = DecimalFormat("###,###")
        res = df.format(res!!.toInt()).toString() + " 원"
        return res
    }

    override fun getItemCount(): Int = recommendProductsList.size
}