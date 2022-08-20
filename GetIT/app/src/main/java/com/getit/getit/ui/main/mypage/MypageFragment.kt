package com.getit.getit.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.mypage.like.LikeProductAcitivity
import com.getit.getit.ui.main.mypage.review.ReviewProductAcitivity
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
                    val intent = Intent(context, LikeProductAcitivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.review_btn -> {
                activity?.let {
                    val intent = Intent(context, ReviewProductAcitivity::class.java)
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

        val name = view?.findViewById<TextView>(R.id.name)
        val nickname = view?.findViewById<TextView>(R.id.nickname)

        val mypageRetrofit = retrofit.create(MypageService::class.java)
        mypageRetrofit.getResponse().enqueue(object : Callback<UserInfo> {

        val mypage_like_image_1 = view?.findViewById<ImageView>(R.id.imagelike1)
        val mypage_like_image_2 = view?.findViewById<ImageView>(R.id.imagelike2)
        val mypage_like_image_3 = view?.findViewById<ImageView>(R.id.imagelike3)
        val mypage_like_text_1 = view?.findViewById<TextView>(R.id.product_like_1)
        val mypage_like_text_2 = view?.findViewById<TextView>(R.id.product_like_2)
        val mypage_like_text_3 = view?.findViewById<TextView>(R.id.product_like_3)
        val mypage_price_1 = view?.findViewById<TextView>(R.id.product_price_1)
        val mypage_price_2 = view?.findViewById<TextView>(R.id.product_price_2)
        val mypage_price_3 = view?.findViewById<TextView>(R.id.product_price_3)


            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        name?.text = body.result?.email.toString()
                        nickname?.text = body.result?.nickname.toString()

                        if(body.result?.likeProduct==null){

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


