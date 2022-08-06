package com.getit.getit.ui.main

import android.R
import android.widget.ArrayAdapter
import com.getit.getit.databinding.ActivitySearchBinding
import com.getit.getit.ui.BaseActivity

class SearchActivity: BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {
    override fun initAfterBinding() {

        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        // 검색어 자동완성
        autocompleteTerms()

        // 검색어 삭제
        binding.deleteBtnIb.setOnClickListener {
            resetTerms()
        }
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

}

