package com.getit.getit.ui.main.home.server

import retrofit2.Call
import retrofit2.http.GET

interface MainRetrofitInterface {
    @GET("/main/recommproducts")
    fun getMainRecommend(): Call<MainRecommendProductsResponse>

}