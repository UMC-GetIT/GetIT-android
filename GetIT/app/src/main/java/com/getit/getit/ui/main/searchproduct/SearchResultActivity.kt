package com.getit.getit.ui.main.searchproduct

import android.util.Log
import com.getit.getit.databinding.ActivitySearchResultBinding
import com.getit.getit.ui.BaseActivity

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>(ActivitySearchResultBinding::inflate), SearchProductView {
    private lateinit var searchProductRVAdapter: SearchProductRVAdapter
    private lateinit var keyword: String

    override fun initAfterBinding() {
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        if (intent.hasExtra("keyword")) {
            keyword = intent.getStringExtra("keyword").toString()
            binding.title.text = keyword
        }

        getProducts(keyword)
    }

    private fun getProducts(keyword: String) {
        val searchProductService = SearchProductService()
        searchProductService.setSearchProductView(this) // <- 문제
        searchProductService.getSearchProducts(keyword)
    }

    private fun initRecyclerView(result: List<SearchProductResult>) {
        searchProductRVAdapter = SearchProductRVAdapter(this, result)
        binding.searchResultRecyclerView.adapter = searchProductRVAdapter
    }

    override fun onGetSearchProductLoading() {
    }

    override fun onGetSearchProductSuccess(code: Int, result: List<SearchProductResult>) {
        initRecyclerView(result)
    }

    override fun onGetSearchProductFailure(code: Int, message: String) {
    }
}
