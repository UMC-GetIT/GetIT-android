package com.getit.getit.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.getit.getit.ApplicationClass.Companion.retrofit
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.mypage.like.LikeProductAcitivity
import com.getit.getit.ui.main.mypage.review.ReviewProductAcitivity
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity
import com.getit.getit.ui.main.mypage.settings.SettingActivity
import com.getit.getit.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MypageFragment() : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate), View.OnClickListener {
    //button 클릭시
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //데이터 받아오기
        MypageData()


        setOnClickListener()
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


    private fun setOnClickListener() {
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
        val spf = this.activity?.getSharedPreferences("auth" , AppCompatActivity.MODE_PRIVATE);
        var accessToken = spf?.getString("accessToken", "").toString()
        Log.d("테스트", accessToken)

        //client 생성-token 값 인증
        val okHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor("Bearer",jwt)).build()

        //client와 retrofit의 인스턴스 연결
        val retrofit2 : Retrofit by lazy {
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val mypageRetrofit = retrofit.create(Service::class.java)
        mypageRetrofit.getResponse().enqueue(object: Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.d("결과",response.body().toString())
                Log.d("결과",response.toString())

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        Log.d("결과",body.toString())
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

