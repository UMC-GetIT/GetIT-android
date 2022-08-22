package com.getit.getit.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.mypage.like.LikeProductActivity
import com.getit.getit.ui.main.mypage.review.ReviewProductActivity
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity
import com.getit.getit.ui.main.mypage.settings.SettingActivity
import com.getit.getit.utils.ApplicationClass.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MypageFragment() : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate),
    View.OnClickListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MypageData()

        setOnClickListenerAtMyPage()

        val toolbar: Toolbar = binding.toolbar
        toolbar.title = "내 정보"
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_setting -> {
                    startActivity(Intent(context, SettingActivity::class.java))
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
                    val intent = Intent(context, ChangeProfileActivity::class.java)
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

    fun MypageData() {

        val name = binding.name
        val nickname = binding.nickname
        val mypage_like_image_1 = binding.imagelike1
        val mypage_like_image_2 = binding.imagelike2
        val mypage_like_image_3 = binding.imagelike3
        val mypage_like_text_1 = binding.productLike1
        val mypage_like_text_2 = binding.productLike2
        val mypage_like_text_3 = binding.productLike3
        val mypage_price_1 = binding.productPrice1
        val mypage_price_2 = binding.productPrice2
        val mypage_price_3 = binding.productPrice3
        val mypage_reivew_name_1=binding.productReviewName1
        val mypage_review_name_2=binding.productReviewName2
        val mypage_review_text_1=binding.productReviewText1
        val mypage_review_text_2=binding.productReviewText2


        val mypageRetrofit = retrofit.create(MypageService::class.java)
        mypageRetrofit.getResponse().enqueue(object : Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    Log.d("테스트",response.body().toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        binding.name?.text = body.result?.email.toString()
                        binding.nickname?.text = body.result?.nickname.toString()
                        binding.productLike1?.text=body.result?.likeProduct?.toString()//name 넣어야 함



                        if(body.result?.likeProduct?.isEmpty()==true){
                            binding.likeLinearlayout1.setVisibility(View.INVISIBLE)
                            binding.frameLike1.setVisibility(View.INVISIBLE)
                            binding.frameLike2.setVisibility(View.INVISIBLE)
                            binding.frameLike3.setVisibility(View.INVISIBLE)
                            binding.likeNoProduct.setVisibility(View.VISIBLE)

                        }
                        if(body.result?.review?.isEmpty()==true){
                            binding.likeLinearlayout1.setVisibility(View.INVISIBLE)
                            binding.frameReview2.setVisibility(View.INVISIBLE)
                            binding.reviewNoProduct.setVisibility(View.VISIBLE)
                        }
                        else{
                            binding.likeLinearlayout1.setVisibility(View.VISIBLE)
                            binding.frameReview2.setVisibility(View.VISIBLE)
                            binding.reviewNoProduct.setVisibility(View.INVISIBLE)
                            binding.likeLinearlayout1.setVisibility(View.INVISIBLE)
                            binding.frameLike1.setVisibility(View.VISIBLE)
                            binding.frameLike2.setVisibility(View.VISIBLE)
                            binding.frameLike3.setVisibility(View.VISIBLE)
                            binding.likeNoProduct.setVisibility(View.INVISIBLE)


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


