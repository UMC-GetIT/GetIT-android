package com.getit.getit.ui.main.mypage.review

import com.getit.getit.ui.main.mypage.like.LikeApiService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ReviewApiService {
    @GET("/users/review")
    fun getReviewProducts(): Call<ReviewProducts>

    @DELETE("/products/delete/:reviewIdx")
    fun reviewDelete():Call<DeleteReview>

}