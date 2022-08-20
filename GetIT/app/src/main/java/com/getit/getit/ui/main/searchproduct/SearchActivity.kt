package com.getit.getit.ui.main.searchproduct

import android.R
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.getit.getit.databinding.ActivitySearchBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.category.CategoryService

class SearchActivity: BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate), RecommendView {
    private lateinit var recommendAdapter: RecommendRVAdapter

    override fun initAfterBinding() {
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        setListenerToEditText()

        autocompleteTerms()

        binding.deleteBtnIb.setOnClickListener {
            resetTerms()
        }

        getRecommend()
    }

    private fun initRecyclerView(result: List<RecommendResult>) {
        recommendAdapter = RecommendRVAdapter(this, result)
        binding.listview.adapter = recommendAdapter
    }

    private fun getRecommend() {
        val recommendService = CategoryService()
        recommendService.setRecommendView(this)
        recommendService.getRecommend()
    }

    private fun setListenerToEditText() {
        binding.searchEdittextEt.setOnKeyListener { _, keyCode, event ->
            // Enter Key Action
            val keyword = binding.searchEdittextEt.text.toString()
            if (keyword != "" && event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                // 키패드 내리기
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.searchEdittextEt.windowToken, 0)

                activityChange(binding.searchEdittextEt.text.trim().toString())
                true
            }
            false
        }
    }

    private fun activityChange(keyword: String) {
        val intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra("keyword", keyword)
        startActivity(intent)
    }

    private fun autocompleteTerms() {
        var items = arrayOf("삼성전자", "삼성 노트북", "LG", "LG 노트북", "삼성 갤럭시")
        var autoCompleteTextView = binding.searchEdittextEt

        var adpater = ArrayAdapter<String>(this, R.layout.simple_dropdown_item_1line, items)
        autoCompleteTextView.setAdapter(adpater)
    }

    private fun resetTerms() {
        binding.searchEdittextEt.text = null
    }

    override fun onGetRecommendLoading() {
        TODO("Not yet implemented")
    }

    override fun onGetRecommendSuccess(code: Int, result: List<RecommendResult>) {
        Log.d("TEST", result.toString())
        initRecyclerView(result)
    }

    override fun onGetRecommendFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }

}

