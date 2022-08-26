package com.getit.getit.ui.main.mypage.review

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.R
import com.getit.getit.ui.main.category.detail.ProductDetailActivity
import java.text.DecimalFormat


class ReviewpdRVAdapter(val ReviewList : List<result>, val context : Context) : RecyclerView.Adapter<ReviewpdRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ReviewpdRVAdapter.ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_mypage_reviewlist
                    ,parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ReviewList[position], context)

    }

    override fun getItemCount(): Int{
        return ReviewList.count()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val name = itemView?.findViewById<TextView>(R.id.item_name)
        val review = itemView?.findViewById<TextView>(R.id.item_review)
        val deletebtn = itemView?.findViewById<ImageButton>(R.id.review_delete_btn)

        fun bind(itemReviewProducts: result?, context: Context) {
//            val price = itemReviewProducts!!.price
//            val df = DecimalFormat("###,###")
//            val strPrice = df.format(price.toInt()) + " 원"
//            itemView.setOnClickListener{
//                val intent = Intent(context, ProductDetailActivity::class.java)
//                intent.putExtra("productId", itemReviewProducts!!.productId)
//                intent.putExtra("productName", itemReviewProducts.productname)
//                intent.putExtra("price", strPrice)
//                intent.putExtra("imageUrl", itemReviewProducts.productImgUrl)
//                ContextCompat.startActivity(itemView.context, intent, null)
//            }

            name?.text = itemReviewProducts?.productname
            review?.text=itemReviewProducts?.review
            deletebtn?.setOnClickListener{
                var dialog = AlertDialog.Builder(context)
                dialog.setTitle("삭제 후에는 복구할 수 없습니다. ")
                dialog.setMessage("정말 삭제하시겠습니까?")

                var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                    if (DialogInterface.BUTTON_POSITIVE == p1) {
                        ReviewDeleteDialog().reviewdelete()
                        Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.setPositiveButton("삭제", dialogLister)
                dialog.setNegativeButton("취소", null)
                dialog.show()

            }
        }
    }

}



/*var datalist = mutableListOf<ReviewProducts>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가

    inner class MyViewHolder(private val binding: MypageProductReviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(ReviewProducts: ReviewProducts){
            //binding.img.=ReviewProducts.img
            binding.itemName.text=ReviewProducts.name
            binding.itemReview.text=ReviewProducts.review
            binding.imageReview.setImageResource(ReviewProducts.img!!)
        }
    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= MypageProductReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
//적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }*/
