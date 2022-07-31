package com.getit.getit.ui.main.comparison

import com.getit.getit.databinding.FragmentComparisonBinding
import com.getit.getit.ui.BaseFragment


class ComparisonFragment(): BaseFragment<FragmentComparisonBinding>(FragmentComparisonBinding::inflate) {

    override fun initAfterBinding() {
    }

    override fun onResume() {
        super.onResume()
        showActionBar() //지난번에 넣은 코드에서 이 부분만 추가
        setActionBarTitle("비교하기")
    }
}