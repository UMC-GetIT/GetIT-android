package com.getit.getit.ui.main.home

import android.os.Bundle
import android.view.View
import com.getit.getit.databinding.FragmentHomeBinding
import com.getit.getit.ui.BaseFragment


class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initAfterBinding() {

    }
}