package com.getit.getit.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemSearchBinding

class SearchRVAdapter(private val productList: ArrayList<Products>) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {
        // * position: index id
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Products) {
            binding.itemSearchProductNameTv.text = product.name
            binding.itemSearchProductPriceTv.text = product.price.toString()
        }
    }
}