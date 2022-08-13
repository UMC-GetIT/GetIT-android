package com.getit.getit.ui.main

import com.getit.getit.databinding.ActivitySearchResultBinding
import com.getit.getit.ui.BaseActivity

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>(ActivitySearchResultBinding::inflate) {
    override fun initAfterBinding() {
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }
    }
}
