package com.getit.getit.ui.main.mypage.review

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.MypageReviewListBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.mypage.like.LikeProducts

class ReviewProductAcitivity : BaseActivity<MypageReviewListBinding>(MypageReviewListBinding::inflate) {
    val mDatas=mutableListOf<ReviewProducts>()



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
            add(ReviewProducts(R.drawable.samsong_labtop_img,"삼성전자 2021 갤럭시북 프로 360 13.3 + S펜","너무좋네요" ))
            add(ReviewProducts(R.drawable.lg_labtop_img,"LG전자 2022 그램 16","너무좋네요"))
            add(ReviewProducts(R.drawable.samsong_pro_labtop_img,"삼성전자 2021 갤럭시 북 프로 360","너무좋네요"))
            add(ReviewProducts(R.drawable.lg_gram_labtop_img,"2022 LG 그램 360 터치스크린 노트북", "너무좋네요" ))
            add(ReviewProducts(R.drawable.apple_labtop_img,"Apple 2021 맥북프로 14" ,"너무좋네요" ))
            add(ReviewProducts( R.drawable.legion_labtop_img,"레노버 Legion i7","너무좋네요"))
            add(ReviewProducts(R.drawable.apple_labtop2_img,"Apple 2021 맥북프로 14","너무좋네요"))
            add(ReviewProducts(R.drawable.samsong2_labtop_img,"풀스펙 NT371B5J 램16G SSD512G 윈도우10","너무좋네요"))
        }
    }

        fun initRecyclerView(){
            val adapter=ReviewpdRVAdapter() //어댑터 객체 만듦
            adapter.datalist=mDatas //데이터 넣어줌
            binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
            binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
        }
    override fun initAfterBinding() {}

    }