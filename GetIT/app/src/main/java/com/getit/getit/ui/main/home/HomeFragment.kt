package com.getit.getit.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.flo.ui.main.home.BannerFragment
import com.example.flo.ui.main.home.BannerVPAdapter
import com.getit.getit.R
import com.getit.getit.databinding.FragmentHomeBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.category.detail.ProductDetailActivity
import com.getit.getit.ui.main.home.data.ItTermIcon
import com.getit.getit.ui.main.home.itterm.ItTermWindowActivity
import com.getit.getit.ui.main.home.itterm.TermRVAdapter
import com.getit.getit.ui.main.home.recommend.RecommendActivity
import com.getit.getit.ui.main.home.server.MainRecommendResult
import com.getit.getit.ui.main.home.server.MainRecommendService
import java.text.DecimalFormat


class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), HomeView {

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

        binding.questionRecommendationContentButton.setOnClickListener{
            startActivity(Intent(activity, RecommendActivity::class.java))
        }

//        binding.answer11.setOnClickListener{
//            startActivity(Intent(activity, WindowActivity::class.java))
//        }

        (activity as MainActivity).setLogoVisible(View.VISIBLE);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initAfterBinding() {
        setItTermIcon()


        val homeService = MainRecommendService()
        homeService.setHomeView(this)

        homeService.loadHomeRecommendProducts();

    }

    private fun setItTermIcon() {
        var ittermDatas = ArrayList<ItTermIcon>()

        ittermDatas.apply {
            add(ItTermIcon(R.drawable.cpu_icon, getString(R.string.cpu)))
            add(ItTermIcon(R.drawable.ram_icon, getString(R.string.ram)))
            add(ItTermIcon(R.drawable.gpu_icon, getString(R.string.gpu)))
            add(ItTermIcon(R.drawable.ssd_icon, getString(R.string.save)))
            add(ItTermIcon(R.drawable.output_icon, getString(R.string.output)))
            add(ItTermIcon(R.drawable.terminal_icon, getString(R.string.terminal)))
            add(ItTermIcon(R.drawable.protocol_icon, getString(R.string.protocol)))
            add(ItTermIcon(R.drawable.resolution_icon, getString(R.string.resolution)))
            add(ItTermIcon(R.drawable.pixel_icon, getString(R.string.pixel)))
        }

        val termRVAdapter = this.activity?.let { TermRVAdapter(ittermDatas, it) }
        binding.ittermAnswerRv.adapter = termRVAdapter

        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.ittermAnswerRv.layoutManager = linearLayoutManager
    }


    override fun onResume() {
        super.onResume()
        showActionBar()
        setActionBarTitle("")
    }

    override fun setMainRecommendProducts(code: Int, result: MainRecommendResult) {
        when(result.topic){
            "최신 노트북" -> binding.recommendQuestionTv.text = "신상 노트북에 눈돌아가는 중 +_+"
            "무선 이어폰" -> binding.recommendQuestionTv.text = "설마 아직도 줄 이어폰 쓰세요?"
            "스피커" -> binding.recommendQuestionTv.text = "감성 있게 노래 듣고 싶을땐..."
            "최신 핸드폰" -> binding.recommendQuestionTv.text = "요즘 인싸들은 이런 핸드폰 쓴다면서요?"
            "게이밍 PC" ->  binding.recommendQuestionTv.text = "게임은 장비빨이죠"
       }
        Glide.with(this).load(result.products[0].imageUrl).into(binding.recommendAnswer1Iv)
        Glide.with(this).load(result.products[1].imageUrl).into(binding.recommendAnswer2Iv)
        Glide.with(this).load(result.products[2].imageUrl).into(binding.recommendAnswer3Iv)
        Glide.with(this).load(result.products[3].imageUrl).into(binding.recommendAnswer4Iv)

        binding.recommendAnswer1Tv.text = arrangeName(result.products[0].name)
        binding.recommendAnswer2Tv.text = arrangeName(result.products[1].name)
        binding.recommendAnswer3Tv.text = arrangeName(result.products[2].name)
        binding.recommendAnswer4Tv.text = arrangeName(result.products[3].name)

        // 상품 클릭
        binding.recommendAnswer1.setOnClickListener {
            ClickProduct(0, result)
        }
        binding.recommendAnswer2.setOnClickListener {
            ClickProduct(1, result)
        }
        binding.recommendAnswer3.setOnClickListener {
            ClickProduct(2, result)
        }
        binding.recommendAnswer4.setOnClickListener {
            ClickProduct(3, result)
        }
    }

    private fun ClickProduct(index: Int, result: MainRecommendResult) {
        var price = result.products[index].lprice
        val df = DecimalFormat("###,###")
        val strPrice = df.format(price.toInt()) + " 원"
        var intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("productId", result.products[index].productId)
        intent.putExtra("productName", arrangeName(result.products[index].name))
        intent.putExtra("price", strPrice)
        intent.putExtra("imageUrl", result.products[index].imageUrl)
        startActivity(intent)
    }

    private fun arrangeName(name : String) : String{
        return name.replace("<b>", "").replace("</b>","")
    }
}
