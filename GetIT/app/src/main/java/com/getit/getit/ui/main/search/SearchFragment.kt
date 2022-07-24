package com.getit.getit.ui.main.search

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentSearchBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.google.gson.Gson

class SearchFragment(): BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

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

        // 상품 카테고리 클릭
        // 휴대폰 클릭


        //카테고리 선택
//        val company = binding.searchCompanySpinner
//        val cpu = binding.searchCpuSpinner
//        val ram = binding.searchRamSpinner
//        val releaseYear = binding.searchReleaseYearSpinner

        //어댑터 - resource, array.xml에 있는 아이템 목록을 추가
//        company.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.companyList, android.R.layout.simple_spinner_item)
//        cpu.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.cpuList, android.R.layout.simple_spinner_item)
//        ram.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.ramList, android.R.layout.simple_spinner_item)
//        releaseYear.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.releaseYearList, android.R.layout.simple_spinner_item)

        //Listener - 어떤 아이템을 눌렀는지 확인
//        company.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                when (position) {
//                    0 -> {
//                    }
//                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//            }
//
//        }
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
        (activity as MainActivity).setActionBarTitle("제품 조회")
    }



}