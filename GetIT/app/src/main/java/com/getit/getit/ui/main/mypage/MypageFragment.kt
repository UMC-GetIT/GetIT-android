package com.getit.getit.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.children
import com.getit.getit.ApplicationClass.Companion.TAG
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity
import com.getit.getit.ui.main.mypage.settings.settingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate), View.OnClickListener{
    //button 클릭시
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        val btnSequence = binding.container.children
        btnSequence.forEach { btn ->
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
            R.id.setting_button -> {
                activity?.let {
                    val intent = Intent(context, settingActivity::class.java)
                    startActivity(intent)
                }
            }

            R.id.like_btn -> {
                activity?.let {
                    val intent = Intent(context, settingActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.review_btn -> {
                activity?.let {
                    val intent = Intent(context, settingActivity::class.java)
                    startActivity(intent)
                }
            }

        }

    }
        // main 액션바 제거
        override fun onResume() {
            super.onResume()
            (activity as MainActivity).hideActionBar()
        }
/*
        private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
        private val api: Service = retrofit.create(Service::class.java) // retrofit이 interface 구현
        private val authToken = "토큰값을 여기 작성"

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            // retrofit setting
            Runnable {
                api.getResponse("1", "Bearer $authToken").enqueue(object : Callback<ResponseData> {
                    // 전송 실패
                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Log.d("태그", t.message!!)
                    }
                    // 전송 성공
                    override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                        Log.d("태그", "response : ${response.body()?.result}") // 정상출력

                        // 전송은 성공 but 서버 4xx 에러
                        Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                        Log.d("태그: 메시지", "response : ${response.message()}")
                        Log.d("태그: 코드", "response : ${response.code()}")
                    }
                })
            }.run()
        }*/

    override fun initAfterBinding() {
        }

    companion object {
        private const val TAG = "MypageFragment"
        fun instance() = MypageFragment()

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideActionBar()

    }
    }