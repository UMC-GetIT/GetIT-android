package com.getit.getit.ui.main.mypage.settings.changeProfile.name

import android.util.Log
import android.widget.Toast
import com.getit.getit.ui.main.mypage.settings.changeProfile.ChangeProfileApi
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class newnameRetrofit {
    fun changeData(change:newname) {
        val newpwdRetrofit = ApplicationClass.retrofit.create(ChangenameApi::class.java)
        newpwdRetrofit.newnickname(change).enqueue(object : Callback<responsename> {
            override fun onResponse(call: Call<responsename>, response: Response<responsename>) {
                if (response.isSuccessful) {
                    Log.d("성공", response.body().toString())


                } else {
                    Log.d("실패", response.body().toString())

                }
            }

            override fun onFailure(call: Call<responsename>, t: Throwable) {

            }
        })
    }
}
