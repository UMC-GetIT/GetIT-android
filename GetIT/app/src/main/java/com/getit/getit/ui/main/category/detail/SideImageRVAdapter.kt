package com.getit.getit.ui.main.category.detail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getit.getit.R
import com.getit.getit.databinding.ItemProductDetailImageBinding

class SideImageRVAdapter(val context: Context, val images : List<String>): RecyclerView.Adapter<SideImageRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(imageUrl: String)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SideImageRVAdapter.ViewHolder {
        val binding: ItemProductDetailImageBinding = ItemProductDetailImageBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SideImageRVAdapter.ViewHolder, position: Int) {
        if (images[position] == "" || images[position] == null) { }
        else {
            Glide.with(context).load(images[position]).into(holder.sideImage)
        }

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(images[position])
            Log.d("LOG", position.toString())
        }

        // 초기 선택 이미지 이벤트
//        if (position == 0) {
//            holder.cardView.setStrokeColor(R.color.primary)
//        }
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(val binding: ItemProductDetailImageBinding): RecyclerView.ViewHolder(binding.root) {
        val sideImage: ImageView = binding.sideImageIv
        val cardView: CardView = binding.productDetailSideImgCardview
    }
}