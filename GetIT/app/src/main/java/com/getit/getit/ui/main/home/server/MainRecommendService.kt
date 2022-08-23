package com.getit.getit.ui.main.home.server

import android.util.Log
import com.getit.getit.ui.login.LoginActivity
import com.getit.getit.ui.main.home.HomeView
import com.getit.getit.utils.ApplicationClass.Companion.retrofit
import com.getit.getit.utils.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRecommendService {
    private lateinit var homeView: HomeView

    fun setHomeView(homeView: HomeView) {
        this.homeView = homeView
    }

    fun loadHomeRecommendProducts() {
        val homeService = retrofit.create(MainRetrofitInterface::class.java)
        homeService.getMainRecommend()
            .enqueue(object : Callback<MainRecommendProductsResponse> {
                override fun onResponse(
                    call: Call<MainRecommendProductsResponse>,
                    response: Response<MainRecommendProductsResponse>
                ) {
                    Log.d("테스트", response.toString())
                    Log.d("테스트", response.isSuccessful.toString())
                    if (!response.isSuccessful) {
                        Log.d("테스트", "재발재발")
                        var loginActivity = LoginActivity()
                        loginActivity.autoLoginMedium();
                        loadHomeRecommendProducts()
                    } else {
                        val resp: MainRecommendProductsResponse = response.body()!!
                        Log.d("테스트2", resp.toString())

                        when (resp.code) {
                            1000 -> {
                                Log.d("테스트", "진입성공 1000")
                                homeView.setMainRecommendProducts(resp.code, resp.result!!)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MainRecommendProductsResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }
}