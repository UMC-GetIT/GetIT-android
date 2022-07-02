package com.getit.getit.ui.main.search

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentSearchBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity

class SearchFragment(): BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun initAfterBinding() {
        binding.toolbar.root.title = "제품 조회"

        var productDatas = ArrayList<Products>()
        // 더미데이터
        productDatas.apply {
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
            add(Products("삼성 노트북", 50000))
        }

        val searchRVAdatpter = SearchRVAdapter(productDatas)
        binding.searchProductRv.adapter = searchRVAdatpter
        binding.searchProductRv.layoutManager = GridLayoutManager(context, 2)

        binding.laptopIb.setOnClickListener {
            startActivity(Intent(context, ProductDetailActivity::class.java))
        }
    }
}