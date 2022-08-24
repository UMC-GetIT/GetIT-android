package com.getit.getit.ui.main.home

import android.content.Intent
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
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.home.data.ItTermIcon
import com.getit.getit.ui.main.home.recommend.RecommendActivity


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

        // 상품 클릭

    }

    private fun setItTermIcon() {
        var ittermDatas = ArrayList<ItTermIcon>()
        // 더미데이터
        ittermDatas.apply {
            add(ItTermIcon(R.drawable.cpu_icon, "CPU"))
            add(ItTermIcon(R.drawable.ram_icon, "RAM"))
            add(ItTermIcon(R.drawable.gpu_icon, "GPU"))
            add(ItTermIcon(R.drawable.ssd_icon, "저장장치"))
            add(ItTermIcon(R.drawable.output_icon, "출력"))
            add(ItTermIcon(R.drawable.terminal_icon, "단자"))
            add(ItTermIcon(R.drawable.protocol_icon, "통신 규격"))
            add(ItTermIcon(R.drawable.resolution_icon, "해상도"))
            add(ItTermIcon(R.drawable.pixel_icon, "카메라 화소"))
        }

        val termRVAdapter = TermRVAdapter(ittermDatas)
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

        Log.d("테스트",result.products[0].imageUrl)

        binding.recommendAnswer1Tv.text = arrangeName(result.products[0].name)
        binding.recommendAnswer2Tv.text = arrangeName(result.products[1].name)
        binding.recommendAnswer3Tv.text = arrangeName(result.products[2].name)
        binding.recommendAnswer4Tv.text = arrangeName(result.products[3].name)
        // 상품 클릭

    }

    private fun arrangeName(name : String) : String{
        return name.replace("<b>", "").replace("</b>","")
    }
}
