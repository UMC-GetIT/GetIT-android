package com.getit.getit.ui.main.mypage.like

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.MypageLikeListBinding
import com.getit.getit.ui.BaseActivity


class LikeProductAcitivity : BaseActivity<MypageLikeListBinding>(MypageLikeListBinding::inflate) {
    //private lateinit var adapter:RecyclerViewAdapter //adapter객체 먼저 선언해주기!
    val mDatas=mutableListOf<LikeProducts>()



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
            add(LikeProducts(R.drawable.samsong_labtop_img,"삼성전자 2021 갤럭시북 프로 360 13.3 + S펜" ))
            add(LikeProducts(R.drawable.lg_labtop_img,"LG전자 2022 그램 16"))
            add(LikeProducts(R.drawable.samsong_pro_labtop_img,"삼성전자 2021 갤럭시 북 프로 360"))
            add(LikeProducts(R.drawable.lg_gram_labtop_img,"2022 LG 그램 360 터치스크린 노트북",  ))
            add(LikeProducts(R.drawable.apple_labtop_img,"Apple 2021 맥북프로 14"  ))
            add(LikeProducts( R.drawable.legion_labtop_img,"레노버 Legion i7"))
            add(LikeProducts(R.drawable.apple_labtop2_img,"Apple 2021 맥북프로 14"))
            add(LikeProducts(R.drawable.samsong2_labtop_img,"풀스펙 NT371B5J 램16G SSD512G 윈도우10"))
        }
    }

    fun initRecyclerView(){
        val adapter=LikeRVAdatper() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerView.layoutManager= GridLayoutManager(this, 3) //레이아웃 매니저 연결
    }

    override fun initAfterBinding() {}

}