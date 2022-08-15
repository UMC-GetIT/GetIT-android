package com.getit.getit.ui.main.mypage.Like

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LikeInterface {
    @GET("/users/mylike")

    fun getServices(): Call<List<Likeitem>>

    companion object Factory {
        fun create(): LikeInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://52.79.227.36")
                .build()

            return retrofit.create(LikeInterface::class.java)
        }
    }
}