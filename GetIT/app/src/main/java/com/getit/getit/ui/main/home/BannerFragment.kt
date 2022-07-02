package com.example.flo.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.FragmentBannerBinding
import com.getit.getit.ui.BaseFragment

class BannerFragment(val imgRes : Int): BaseFragment<FragmentBannerBinding>(FragmentBannerBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding .bannerImageIv.setImageResource(imgRes)
        return binding.root
    }

    override fun initAfterBinding() {
    }
}