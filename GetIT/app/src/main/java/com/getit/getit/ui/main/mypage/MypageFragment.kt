package com.getit.getit.ui.main.mypage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.getit.getit.ApplicationClass.Companion.retrofit
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypageBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.mypage.like.HeaderInterceptor
import com.getit.getit.ui.main.mypage.like.LikeApiService
import com.getit.getit.ui.main.mypage.like.LikeProductAcitivity
import com.getit.getit.ui.main.mypage.like.LikeProducts
import com.getit.getit.ui.main.mypage.review.ReviewProductAcitivity
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity
import com.getit.getit.ui.main.mypage.settings.PreferenceHelper.get
import com.getit.getit.ui.main.mypage.settings.SettingActivity
import com.getit.getit.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate), View.OnClickListener {
    //button 클릭시
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //데이터 받아오기
        MypageData()


        setOnClickListener()
        val toolbar: Toolbar = binding.toolbar
        toolbar.title = "내 정보"
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
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
        (activity as MainActivity).hideActionBar()
    }

     fun MypageData(){

         val spf = (activity as MainActivity).getSharedPreference();
         var jwt = spf?.getString("jwt","").toString()
         Log.d("테스트", jwt);

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
/* private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
 private val api: Service = retrofit.create(Service::class.java) // retrofit이 interface 구현
 private val authToken = "토큰값을 여기 작성"

 override fun onActivityCreated(savedInstanceState: Bundle?) {
     super.onActivityCreated(savedInstanceState)

     // retrofit setting
     Runnable {
         api.getResponse("1",Bearer $authToken").enqueue(object : Callback<ResponseData> {
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
}
}


