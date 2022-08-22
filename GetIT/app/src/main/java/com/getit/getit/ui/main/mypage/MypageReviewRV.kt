package com.getit.getit.ui.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.R

class MypageReviewRV(val MypageReviewList: List<Review>, val context: Context)
    : RecyclerView.Adapter<MypageReviewRV.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageReviewRV.ViewHolder {
        return MypageReviewRV.ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_mypage_product_review, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(MypageReviewList[position], context)
    }

    override fun getItemCount(): Int {
        return MypageReviewList.count()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val name = itemView?.findViewById<TextView>(R.id.item_name)
        val review = itemView?.findViewById<TextView>(R.id.item_review)

        fun bind(itemLikeProducts: Review, context: Context) {
            review?.text=itemLikeProducts?.review
            name?.text = itemLikeProducts?.reviewList?.name

        }


    }

}