package com.getit.getit.ui.main.search

import android.content.Intent
import com.getit.getit.R
import com.getit.getit.databinding.FragmentSearchBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity

class SearchFragment(): BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun initAfterBinding() {
        binding.toolbar.root.title = "제품 조회"
        binding.laptopIb.setOnClickListener {
            startActivity(Intent(context, ProductDetailActivity::class.java))
        }
    }
}