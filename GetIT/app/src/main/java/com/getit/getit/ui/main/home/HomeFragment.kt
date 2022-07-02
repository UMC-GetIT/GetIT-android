package com.getit.getit.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.ui.main.home.BannerFragment
import com.example.flo.ui.main.home.BannerVPAdapter
import com.getit.getit.R
import com.getit.getit.databinding.FragmentHomeBinding
import com.getit.getit.ui.BaseFragment
import me.relex.circleindicator.CircleIndicator3;


class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_image_1))
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_image_2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_image_3))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        var indicator : CircleIndicator3 = binding.homeIndicator;
        indicator.setViewPager(binding.homeBannerVp)
        indicator.createIndicators(3, 1)

        binding.answer11.setOnClickListener{
            startActivity(Intent(activity, WindowActivity::class.java))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initAfterBinding() {

    }
}