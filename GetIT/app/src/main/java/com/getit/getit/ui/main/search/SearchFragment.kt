package com.getit.getit.ui.main.search

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentSearchBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity

class SearchFragment(): BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun initAfterBinding() {

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

        //카테고리 선택
        val company = binding.searchCompanySpinner
        val cpu = binding.searchCpuSpinner
        val ram = binding.searchRamSpinner
        val releaseYear = binding.searchReleaseYearSpinner

        //어댑터 - resource, array.xml에 있는 아이템 목록을 추가
        company.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.companyList, android.R.layout.simple_spinner_item)
        cpu.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.cpuList, android.R.layout.simple_spinner_item)
        ram.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.ramList, android.R.layout.simple_spinner_item)
        releaseYear.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.releaseYearList, android.R.layout.simple_spinner_item)

        //Listener - 어떤 아이템을 눌렀는지 확인
        company.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }
}