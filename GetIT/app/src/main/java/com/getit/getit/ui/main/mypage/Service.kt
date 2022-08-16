package com.getit.getit.ui.main.mypage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Service {
    @GET("user/mypage")

    fun getResponse(
        @Query("userIdx") userIdx: String,
        @Header("access_token") authToken:String
    ) :Call<ResponseData>
    fun getUserInfo(
        //Access token이 필요한 경우 header에 추가
        //query에 내용을 적어 보내 원하는 데이터를 받아올 수 있음
        @Header("access_token") accessToken: String,
        //@Query("user_uid") userUid: Int
    ): Call<List<ResponseData.Result>>

    //좋아요 품목 목록
    fun getlikeproduct(
        @Header("access_token") accessToken: String,
    ): Call<List<ResponseData.likeProduct>>//바깥이 대괄효로 시작할때 list추가

    //좋아요 품목 목록
    fun getreviewproduct(

        @Header("access_token") accessToken: String,

        ): Call<List<ResponseData.Review>>
}