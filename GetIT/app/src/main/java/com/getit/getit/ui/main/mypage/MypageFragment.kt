package com.getit.getit.ui.main.mypage

import android.os.Bundle
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity


class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {


    override fun initAfterBinding() {
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideActionBar()
    }

}