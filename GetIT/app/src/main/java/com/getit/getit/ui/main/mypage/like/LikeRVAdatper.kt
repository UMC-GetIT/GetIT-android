package com.getit.getit.ui.main.mypage.like

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
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


class LikeRVAdatper(val LikeList : List<likeProduct>, val context : Context)
    : RecyclerView.Adapter<LikeRVAdatper.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LikeRVAdatper.ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_mypage_likelist, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(LikeList[position], context)
    }

    override fun getItemCount(): Int {
        return LikeList.count()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val img = itemView?.findViewById<ImageView>(R.id.itemimg)
        val name = itemView?.findViewById<TextView>(R.id.name)

        fun bind(itemLikeProducts: likeProduct, context: Context) {
            //이미지 url 불러옴
            val urlString = itemLikeProducts?.image.toString()
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


    /*var datalist = mutableListOf<LikeProducts>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
//
    inner class MyViewHolder(private val binding: ListLikeItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(LikeProducts: LikeProducts){
            //binding.img.=LikeProducts.img
            binding.name.text=LikeProducts.name
            binding.itemimg.setImageResource(LikeProducts.img!!)
            //LikeProducts: result로 바꿀지 고민
        }
    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ListLikeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
//적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    /*class ViewHolder (itemView: View? ) : RecyclerView.ViewHolder(itemView!!) {
    val img = itemView?.findViewById<ImageView>(R.id.itemimg)
        val name = itemView?.findViewById<TextView>(R.id.name)

        fun bind(LikeProducts: LikeProducts?,context: Context){
            val urlString = LikeProducts?.url.toString()
            if(!urlString.isEmpty()){
                LikeProducts?.setImageResource
            }
        }
    }*/*/

