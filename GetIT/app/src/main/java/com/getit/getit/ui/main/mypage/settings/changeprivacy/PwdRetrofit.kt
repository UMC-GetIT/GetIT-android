package com.getit.getit.ui.main.mypage.settings.changeprivacy

import android.util.Log
import android.widget.Toast
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PwdRetrofit {
    fun changeData(change:newpwd) {
        val newpwdRetrofit = ApplicationClass.retrofit.create(ChangepwdApi::class.java)
        newpwdRetrofit.newpwd(change).enqueue(object : Callback<pwd> {
            override fun onResponse(call: Call<pwd>, response: Response<pwd>) {
                if (response.isSuccessful) {
                    Log.d("성공", response.body().toString())


                } else {
                    Log.d("실패", response.body().toString())

                }
            }

            override fun onFailure(call: Call<pwd>, t: Throwable) {

            }
        })
    }
}
