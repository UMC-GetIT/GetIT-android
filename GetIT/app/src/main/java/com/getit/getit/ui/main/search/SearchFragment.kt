package com.getit.getit.ui.main.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentSearchBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.google.gson.Gson

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun initAfterBinding() {

        var productDatas = ArrayList<Products>()
        // 더미데이터
        productDatas.apply {
            add(Products("삼성전자 2021 갤럭시북 프로 360 13.3 + S펜", 1787000, R.drawable.samsong_labtop_img))
            add(Products("LG전자 2022 그램 16", 1454000, R.drawable.lg_labtop_img))
            add(Products("삼성전자 2021 갤럭시 북 프로 360", 1787000, R.drawable.samsong_pro_labtop_img))
            add(Products("2022 LG 그램 360 터치스크린 노트북", 1469000, R.drawable.lg_gram_labtop_img))
            add(Products("Apple 2021 맥북프로 14", 2470000, R.drawable.apple_labtop_img))
            add(Products("레노버 Legion i7", 2433150, R.drawable.legion_labtop_img))
            add(Products("Apple 2021 맥북프로 14", 2960000, R.drawable.apple_labtop2_img))
            add(Products("풀스펙 NT371B5J 램16G SSD512G 윈도우10", 1260000, R.drawable.samsong2_labtop_img))
        }

        val searchRVAdatpter = SearchRVAdapter(productDatas)
        binding.searchProductRv.adapter = searchRVAdatpter
        binding.searchProductRv.layoutManager = GridLayoutManager(context, 2)

        // 상품 클릭
        searchRVAdatpter.setMyItemClickListener(object: SearchRVAdapter.MyItemClickListener{
            override fun onItemClick(products: Products) {
                changeProductActivity(products)
            }
        })

        // 제품 카테고리 선택
        binding.phoneCardView.setOnClickListener {
            binding.phoneCardView.setCardBackgroundColor(getColor(requireContext(), R.color.primary))
            binding.phoneTv.setTextColor(getColor(requireContext(), R.color.white))

            // 이렇게 하고 다른 건 색 초기화 하는 거 어케하지?
            binding.laptopCardView.setCardBackgroundColor(null)
            binding.laptopTv.setTextColor(getColor(requireContext(), R.color.normal))
        }

        // 하단 다이얼로그
        binding.searchDetailCategoryScreensizeBtn.setOnClickListener {
            onSlideUpDialog()
        }
    }

    private fun changeProductActivity(products: Products) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        val gson = Gson()
        val productJson = gson.toJson(products)
        intent.putExtra("product", productJson)
        startActivity(intent)
    }

    // 타이틀 변경
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showActionBar()
        (activity as MainActivity).setActionBarTitle("카테고리 조회")
    }

    // 하단 다이얼로그
    private fun onSlideUpDialog() {
        var contentView: View = (activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.dialog_screensize, null)
        val slideupPopup = context?.let {
            SlideUpDialog.Builder(it)
                .setContentView(contentView)
                .create()
        }
        slideupPopup
        slideupPopup!!.show()
        contentView.findViewById<ImageButton>(R.id.dialog_screensize_close_ib).setOnClickListener {
            slideupPopup.dismissAnim()
        }
    }



}