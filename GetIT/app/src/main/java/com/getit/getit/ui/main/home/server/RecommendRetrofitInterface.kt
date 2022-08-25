package com.getit.getit.ui.main.home.server

import com.getit.getit.ui.main.home.data.RecommendAnswerSet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecommendRetrofitInterface {
    @POST("/main/findspec")
    fun getRecommend(@Body answerSet: RecommendAnswerSet): Call<RecommendResponse>

}