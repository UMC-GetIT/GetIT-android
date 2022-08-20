package com.getit.getit.ui.main.mypage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MypageService {
    @GET("/users/mypage")

    fun getResponse( ) :Call<UserInfo>

}