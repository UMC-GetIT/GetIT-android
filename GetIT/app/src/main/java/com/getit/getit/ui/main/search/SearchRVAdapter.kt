package com.getit.getit.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemSearchBinding

class SearchRVAdapter(private val productList: ArrayList<Products>) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>(){

    interface MyItemClickListener{
        fun onItemClick(product: Products)
    }

    private lateinit var mItemClickListener: MyItemClickListener //전달받은 리스너 객체를 저장 -> 어댑터에서 사용하기 위함
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){ //외부에서 전달받을 수 있는 함수
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])

        //클릭 이벤트 (position 활용)
        holder.itemView.setOnClickListener{
            mItemClickListener.onItemClick(productList[position])
        }

    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Products) {
            binding.itemSearchProductNameTv.text = product.name
            binding.itemSearchProductPriceTv.text = product.price.toString()
            binding.itemSearchImgIv.setImageResource(product.coverImg!!)
        }
    }
}