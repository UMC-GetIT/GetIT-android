package com.getit.getit.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.databinding.ItemMypageLikelistBinding
import com.getit.getit.databinding.ItemMypageProductReviewBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.mypage.like.LikeProductActivity
import com.getit.getit.ui.main.mypage.review.ReviewProductActivity
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity
import com.getit.getit.ui.main.mypage.settings.SettingActivity
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MypageFragment() : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate), View.OnClickListener {

    private lateinit var LikeBinding: ItemMypageLikelistBinding
    private lateinit var ReviewBinding: ItemMypageProductReviewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LikeBinding = ItemMypageLikelistBinding.inflate(layoutInflater)
        ReviewBinding = ItemMypageProductReviewBinding.inflate(layoutInflater)

        MypageData()



        setOnClickListenerAtMyPage()

        val toolbar: Toolbar = binding.toolbar
        toolbar.title = "내 정보"
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_setting -> {
                    val intent = Intent(context, SettingActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun setOnClickListenerAtMyPage() {
        val buttonSequence = binding.container.children
        buttonSequence.forEach { btn ->
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.user_btn -> {
                activity?.let {
                    val text = binding.nickname.text.toString()
                    val intent = Intent(context, ChangeProfileActivity::class.java)
                    intent.putExtra("Data",text)
                    startActivity(intent)
                }
            }

            R.id.like_btn -> {
                activity?.let {
                    val intent = Intent(context, LikeProductActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.review_btn -> {
                activity?.let {
                    val intent = Intent(context, ReviewProductActivity::class.java)
                    startActivity(intent)
                }
            }

        }

    }


    override fun onResume() {
        super.onResume()
        hideActionBar()
    }

        fun LikeAdapter(MypageLikeList: List<likeProduct>) {
            val mAdapter = MypageLikeRV(MypageLikeList, requireContext())
            binding.mypageLikeRecyclerview.adapter = mAdapter
            binding.mypageLikeRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.mypageLikeRecyclerview.setHasFixedSize(false)
        }

        fun ReviewAdapter(MypageReviewList: List<Review>) {
            val mAdapter = MypageReviewRV(MypageReviewList, requireContext())
            binding.mypageReviewRecyclerview.adapter = mAdapter
            binding.mypageReviewRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.mypageReviewRecyclerview.setHasFixedSize(false)
        }

    fun MypageData() {

        val mypageRetrofit = ApplicationClass.retrofit.create(MypageService::class.java)
        mypageRetrofit.getResponse().enqueue(object : Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    Log.d("테스트",response.body().toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    //val url = body.result
                        body?.let {
                            Log.d("테스트",response.body().toString())
                        binding.name?.text = body.result?.email.toString()
                        binding.nickname?.text = body.result?.nickname.toString()
                        LikeAdapter(body.result!!.likeProduct)
                        ReviewAdapter(body.result!!.review)


                        if(body.result?.likeProduct.isEmpty()==true){
                            binding.mypageLikeRecyclerview.setVisibility(View.INVISIBLE)
                            binding.likeNoProduct.setVisibility(View.VISIBLE)

                        }
                        if(body.result?.review.isEmpty()==true){
                            binding.mypageReviewRecyclerview.setVisibility(View.INVISIBLE)
                            binding.reviewNoProduct.setVisibility(View.VISIBLE)
                        }
                        else{
                            binding.mypageReviewRecyclerview.setVisibility(View.VISIBLE)
                            binding.mypageLikeRecyclerview.setVisibility(View.VISIBLE)
                            binding.likeNoProduct.setVisibility(View.INVISIBLE)
                            binding.reviewNoProduct.setVisibility(View.INVISIBLE)


                        }

                    }
                }
            }


            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.d("this is error", t.message.toString())
            }
        })
    }

    override fun initAfterBinding() {
    }

    companion object {
        private const val TAG = "MypageFragment"
        fun instance() = MypageFragment()
    }
}


