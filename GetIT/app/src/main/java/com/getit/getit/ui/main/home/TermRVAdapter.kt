package com.getit.getit.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemIttermBinding
import com.getit.getit.ui.main.home.data.ItTermIcon

class TermRVAdapter (private val ittermList: ArrayList<ItTermIcon>) : RecyclerView.Adapter<TermRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemIttermBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itterm: ItTermIcon) {
            binding.ittermAnswerTv.text = itterm.name
            binding.ittermAnswerIcon.setImageResource(itterm.icon!!)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TermRVAdapter.ViewHolder {
        val binding: ItemIttermBinding = ItemIttermBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TermRVAdapter.ViewHolder, position: Int) {
        holder.bind(ittermList[position])
    }

    override fun getItemCount(): Int = ittermList.size

}