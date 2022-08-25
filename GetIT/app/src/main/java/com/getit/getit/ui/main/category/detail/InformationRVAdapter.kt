package com.getit.getit.ui.main.category.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemDetailInformationBinding

class InformationRVAdapter(val context: Context, private val productInfo: MutableList<MutableList<String>>): RecyclerView.Adapter<InformationRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InformationRVAdapter.ViewHolder {
        val binding: ItemDetailInformationBinding = ItemDetailInformationBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InformationRVAdapter.ViewHolder, position: Int) {
        holder.title.text = productInfo[position][0]
        holder.content.text = productInfo[position][1]
    }

    override fun getItemCount(): Int = productInfo.size

    inner class ViewHolder(val binding: ItemDetailInformationBinding): RecyclerView.ViewHolder(binding.root) {
        val title = binding.productDetailInfoTitleTv
        val content = binding.productDetailInfoContentTv
        val item = binding.productDetailInfo
    }
}