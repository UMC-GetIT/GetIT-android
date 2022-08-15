package com.getit.getit.ui.main.mypage.Like

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.R

/*class LikeAdapter (var LikeList: List<Likeitem>,
                   private val LikeAdapterCallback: LikeAdapterCallback)
    : RecyclerView.Adapter<LikeAdapter.ViewHolder>(){
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAdapter.ViewHolder {
        context = parent.context!!
        val view = LayoutInflater.from(context).inflate(R.layout.list_like_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(LikeList.size > 4) {
            4
        } else {
            LikeList.size
        }
    }

    override fun onBindViewHolder(holder: LikeAdapter.ViewHolder, position: Int) {


        holder.thumbnailUrl.setOnClickListener {
            LikeAdapterCallback.onLikeAdapterItemClick(LikeList[position])
        }

        Picasso.get()
            .load(LikeList[position].imageUrl)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(holder.thumbnailUrl)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val thumbnailUrl: ImageView = itemView.findViewById(R.id.facebook_img)
    }

    interface LikeAdapterCallback{
        fun onLikeAdapterItemClick(data: Likeitem)
    }
}*/