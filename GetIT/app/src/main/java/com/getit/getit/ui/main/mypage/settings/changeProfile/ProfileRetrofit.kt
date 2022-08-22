package com.getit.getit.ui.main.mypage.settings.changeProfile

import android.util.Log
import com.getit.getit.utils.ApplicationClass
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRetrofit {



    fun changeData() {
        val newProfileRetrofit = ApplicationClass.retrofit.create(ChangeProfileApi::class.java)
        newProfileRetrofit.changeprofile("","file").enqueue(object : Callback<profile> {
            override fun onResponse(call: Call<profile>, response: Response<profile>) {
                if (response.isSuccessful) {
                    Log.d("성공", response.body().toString())


                } else {
                    Log.d("실패", response.body().toString())

                }
            }

            override fun onFailure(call: Call<profile>, t: Throwable) {
                Log.e("error : ", t.message.toString())

            }
        })
    }
}