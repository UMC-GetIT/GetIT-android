package com.getit.getit.ui.main.mypage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class MypageLikeRV(val MypageLikeList: List<likeProduct>, val context: Context)
    : RecyclerView.Adapter<MypageLikeRV.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageLikeRV.ViewHolder {
        return MypageLikeRV.ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_mypage_product_like, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(MypageLikeList[position], context)
    }

    override fun getItemCount(): Int {
        return MypageLikeList.count()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val img = itemView?.findViewById<ImageView>(R.id.imagelike1)
        val name = itemView?.findViewById<TextView>(R.id.like_product_name)
        val price = itemView?.findViewById<TextView>(R.id.like_product_price)

        fun bind(itemLikeProducts: likeProduct, context: Context) {
            //이미지 url 불러옴
            val urlString = itemLikeProducts?.likeimage.toString()
            if (urlString.isEmpty()) {
                Log.d("사진 데이터 로딩 오류", "다시할것")


            } else {
                //코루틴을 이용해서 string->bitmap으로 변환
                CoroutineScope(Dispatchers.Main).launch {
                    val bitmap = withContext(Dispatchers.IO) {
                        ImageLoader.loadImage(urlString)
                    }
                    img?.setImageBitmap(bitmap)
                }
                //img?.visibility=View.GONE
            }
            name?.text = itemLikeProducts?.name
            price?.text = itemLikeProducts?.price



        }


    }

    object ImageLoader {
        fun loadImage(imageUrl: String): Bitmap? {
            val bmp: Bitmap? = null
            try {
                val url = URL(imageUrl)
                val stream = url.openStream()
                return BitmapFactory.decodeStream(stream)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return bmp
        }
    }
}