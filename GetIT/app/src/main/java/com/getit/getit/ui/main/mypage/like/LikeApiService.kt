package com.getit.getit.ui.main.mypage.like


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface LikeApiService {
    @GET("/users/mylike")


    fun getLikeProducts(): Call<LikeProducts>

}