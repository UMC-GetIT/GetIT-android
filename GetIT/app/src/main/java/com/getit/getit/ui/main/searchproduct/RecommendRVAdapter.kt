package com.getit.getit.ui.main.searchproduct

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemRecommendBinding

class RecommendRVAdapter(val context: Context, val result : List<RecommendResult>) : RecyclerView.Adapter<RecommendRVAdapter.ViewHolder>(){
    interface MyItemClickListener{
        fun searchKeyword(keywordId: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecommendRVAdapter.ViewHolder {
        val binding: ItemRecommendBinding = ItemRecommendBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendRVAdapter.ViewHolder, position: Int) {
        holder.keyword.text = result[position].keyword
        holder.binding.recommendKeyword.setOnClickListener {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra("keyword", result[position].keyword)
            startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = result.size


    inner class ViewHolder(val binding: ItemRecommendBinding): RecyclerView.ViewHolder(binding.root) {
        val keyword: TextView = binding.recommendKeyword
    }
}