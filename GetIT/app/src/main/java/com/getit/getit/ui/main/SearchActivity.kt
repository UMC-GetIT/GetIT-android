package com.getit.getit.ui.main

import android.R
import android.content.Intent
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.getit.getit.databinding.ActivitySearchBinding
import com.getit.getit.ui.BaseActivity

class SearchActivity: BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {
    override fun initAfterBinding() {

        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        // 엔터 클릭 시 화면 전환
        setListenerToEditText()

        // 자동완성
        autocompleteTerms()

        // 검색어 삭제
        binding.deleteBtnIb.setOnClickListener {
            resetTerms()
        }
    }

    private fun setListenerToEditText() {
        binding.searchEdittextEt.setOnKeyListener { view, keyCode, event ->
            // Enter Key Action
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                // 키패드 내리기
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.searchEdittextEt.windowToken, 0)
                // Toast Message
                startActivity(Intent(this, SearchResultActivity::class.java))
                true
            }
            false
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

