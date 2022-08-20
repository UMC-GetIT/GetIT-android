package com.getit.getit.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.login.getJwt
import com.getit.getit.ui.main.mypage.like.LikeProductAcitivity
import com.getit.getit.ui.main.mypage.review.ReviewProductAcitivity
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity
import com.getit.getit.ui.main.mypage.settings.SettingActivity
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


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

        //엑세스 토큰이 필요하시면 이렇게 가져다 쓰시면 됩니다!
        //하지만 제가 자동으로 jwt가 헤더로 넘어가게끔 설정해놔서 필요할지는 모르겠어요
       var accessToken = getJwt().toString();
        Log.d("테스트", accessToken)




        //잠깐 주석처리 해놓겠습니다. 풀어서 써주세요!
        val name = view?.findViewById<TextView>(R.id.name)
        val nickname = view?.findViewById<TextView>(R.id.nickname)
        val mypageRetrofit = ApplicationClass.retrofit.create(MypageService::class.java)
//        //Service class 이름 다른 이름과 겹칠 수 있기 때문에 MyPageService로 변경 부탁드려요!
       mypageRetrofit.getResponse().enqueue(object: Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.d("결과",response.body().toString())
                Log.d("결과",response.toString())

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        Log.d("결과",body.toString())
                        name?.text= body.result?.email.toString()
                        nickname?.text = body.result?.nickname.toString()

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

