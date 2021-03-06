package com.getit.getit.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.ui.main.home.BannerFragment
import com.example.flo.ui.main.home.BannerVPAdapter
import com.getit.getit.R
import com.getit.getit.databinding.FragmentHomeBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.home.data.ItTermIcon


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
        binding.homeBannerVp.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var page = position + 1
                binding.checkPageNum.text = "$page/3"
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

//        binding.answer11.setOnClickListener{
//            startActivity(Intent(activity, WindowActivity::class.java))
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initAfterBinding() {
        var ittermDatas = ArrayList<ItTermIcon>()
        // ???????????????
        ittermDatas.apply {
            add(ItTermIcon(R.drawable.cpu_icon, "CPU"))
            add(ItTermIcon(R.drawable.ram_icon, "RAM"))
            add(ItTermIcon(R.drawable.gpu_icon, "GPU"))
            add(ItTermIcon(R.drawable.ssd_icon, "????????????"))
            add(ItTermIcon(R.drawable.output_icon, "??????"))
            add(ItTermIcon(R.drawable.terminal_icon, "??????"))
            add(ItTermIcon(R.drawable.protocol_icon, "?????? ??????"))
            add(ItTermIcon(R.drawable.resolution_icon, "?????????"))
            add(ItTermIcon(R.drawable.pixel_icon, "????????? ??????"))
        }

        val termRVAdapter = TermRVAdapter(ittermDatas)
        binding.ittermAnswerRv.adapter = termRVAdapter

        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.ittermAnswerRv.layoutManager = linearLayoutManager

        // ?????? ??????

    }

    override fun onResume() {
        super.onResume()
        showActionBar()
        setActionBarTitle("??? IT")
    }
}