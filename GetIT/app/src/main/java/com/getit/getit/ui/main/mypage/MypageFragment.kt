package com.getit.getit.ui.main.mypage

import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment


class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {

    override fun initAfterBinding() {
        binding.toolbar.root.title = "마이페이지"
    }
}