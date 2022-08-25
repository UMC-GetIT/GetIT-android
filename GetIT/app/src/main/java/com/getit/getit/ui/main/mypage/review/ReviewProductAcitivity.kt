package com.getit.getit.ui.main.mypage.review

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.ActivityMypageReviewListBinding
import com.getit.getit.databinding.ItemMypageProductReviewBinding
import com.getit.getit.databinding.ItemMypageReviewlistBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReviewProductActivity : BaseActivity<ActivityMypageReviewListBinding>(ActivityMypageReviewListBinding::inflate) {

    lateinit var DeleteBinding:ItemMypageReviewlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DeleteBinding = ItemMypageReviewlistBinding.inflate(layoutInflater)
        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        val deletebtn = findViewById<ImageButton>(R.id.review_delete_btn)

        DeleteBinding.reviewDeleteBtn.setOnClickListener{
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("삭제 후에는 복구할 수 없습니다. ")
            dialog.setMessage("정말 삭제하시겠습니까?")

            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                if (DialogInterface.BUTTON_POSITIVE == p1) {
                    ReviewDeleteDialog().reviewdelete()
                    toast()
                }
            }
            dialog.setPositiveButton("삭제", dialogLister)
            dialog.setNegativeButton("취소", null)
            dialog.show()
        }



        //데이터불러오는 함수
        loadReviewData()

    }

    private fun setAdapter(ReviewList : List<result>){
        val mAdapter =ReviewpdRVAdapter(ReviewList,this)
        binding.userReviewRecyclerView.adapter = mAdapter
        binding.userReviewRecyclerView.layoutManager =  LinearLayoutManager(this)
        binding.userReviewRecyclerView.setHasFixedSize(false)
    }

    private fun loadReviewData(){

        val mypageRetrofit = ApplicationClass.retrofit.create(ReviewApiService::class.java)
        mypageRetrofit.getReviewProducts().enqueue(object : Callback<ReviewProducts> {


            override fun onResponse(call: Call<ReviewProducts>, response: Response<ReviewProducts>) {
                Log.d("테스트",response.body().toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        Log.d("테스트",response.body().toString())
                        setAdapter(it.result)


                        if(body.result?.isEmpty()==true){
                            binding.userReviewNoProduct.setVisibility(View.VISIBLE)
                            binding.userReviewRecyclerView.setVisibility(View.INVISIBLE)
                        }

                        else{
                            binding.userReviewNoProduct.setVisibility(View.INVISIBLE)
                            binding.userReviewRecyclerView.setVisibility(View.VISIBLE)
                        }

                    }
                }
            }

            override fun onFailure(call: Call<ReviewProducts>, t: Throwable) {

            }
        })
    }

    fun toast() {
        showToast("삭제되었습니다")
        //Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()

    }

    fun dialog() {
        var dialog = AlertDialog.Builder(this)
        dialog.setTitle("삭제 후에는 복구할 수 없습니다. ")
        dialog.setMessage("정말 삭제하시겠습니까?")

        var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
            if (DialogInterface.BUTTON_POSITIVE == p1) {
                ReviewDeleteDialog().reviewdelete()
                toast()
            }
        }
        dialog.setPositiveButton("삭제", dialogLister)
        dialog.setNegativeButton("취소", null)
        dialog.show()

    }

    override fun initAfterBinding() {
    }
}
/*
        val builder =  OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNDNAZ21haWwuY29tIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY2MDgwODE2N30.61VDBu7vaQHJtZGl38JcwrFK9jQdmBIRM8uuyblm2lCEh9I1qoHTTlRMphZKWhEwhc8NmgMiK_rsjBaDjm4zsg"))
        builder.build()*/



//client 생성
/*//val okHttpClient = OkHttpClient.Builder().addInterceptor(XAccessTokenInterceptor()).build()

 //client와 retrofit의 인스턴스 연결
 val retrofit2 : Retrofit by lazy {
     Retrofit.Builder()
         .client(okHttpClient)
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
 }*/
        //성공했던 코드
        /*val retrofit2 = Retrofit.Builder()
            .baseUrl("http://13.125.69.126")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        /*val retrofitService = ApplicationClass.retrofit.create(ReviewApiService::class.java)
        val call = retrofitService.getReviewProducts()
        call.enqueue(object : Callback<ReviewProducts>{override fun onResponse(call: Call<ReviewProducts>, response: Response<ReviewProducts>) {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    Log.d("결과",body.toString())
                    setAdapter(it.result)
                }
            }
        }

            override fun onFailure(call: Call<ReviewProducts>, t: Throwable) {
                Log.d("this is error", t.message.toString())
            }})
    }*/


    /*val mDatas=mutableListOf<ReviewProducts>()



    override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)

     //뒤로가기 버튼
     binding.backspaceBtn.setOnClickListener {
         backspace()
     }

     ProductsData()
     initRecyclerView()

    }

    private fun ProductsData() {
     with(mDatas) {
         add(ReviewProducts(R.drawable.ipad_1,"Apple 아이패드프로11 3세대 M1","아이패드 화면이 넓어서 \n그림 작업하기에 너무 좋아요"))
         add(ReviewProducts(R.drawable.speaker_1,"JBL Flip 6","스피커 디자인이 예쁘고 소리도 잘 들려요"))
         add(ReviewProducts(R.drawable.samsong_labtop_img,"삼성전자 2021 갤럭시북 프로 360 13.3 + S펜","너무좋네요" ))
         add(ReviewProducts(R.drawable.lg_labtop_img,"LG전자 2022 그램 16","가볍고 들고 다니기 좋아요"))
         add(ReviewProducts(R.drawable.samsong_pro_labtop_img,"삼성전자 2021 갤럭시 북 프로 360","360도로 돌아가서 편리해요"))
         add(ReviewProducts(R.drawable.lg_gram_labtop_img,"2022 LG 그램 360 터치스크린 노트북", "노트북인데도 터치가 되서 좋아요" ))
         add(ReviewProducts(R.drawable.apple_labtop_img,"Apple 2021 맥북프로 16" ,"평소에 맥북 쓰고 싶었는데 \n직접 사용해보니 좋네요" ))
         add(ReviewProducts( R.drawable.legion_labtop_img,"레노버 Legion i7","게임할때 좋아요"))
         add(ReviewProducts(R.drawable.apple_labtop2_img,"Apple 2021 맥북프로 14","맥북 좋아요"))
         add(ReviewProducts(R.drawable.samsong2_labtop_img,"풀스펙 NT371B5J 램16G SSD512G 윈도우10","풀스택이라 편해요"))
     }
    }

     fun initRecyclerView(){
         val adapter=ReviewpdRVAdapter() //어댑터 객체 만듦
         adapter.datalist=mDatas //데이터 넣어줌
         binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
         binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결

     //Glide.with(context).load(item.item_image).into(holder.binding.itemImageview)
     }*/
