package com.getit.getit.ui.main.home.server

import android.util.Log
import com.getit.getit.ui.main.home.data.RecommendAnswerSet
import com.getit.getit.ui.main.home.recommend.RecommendProductsView
import com.getit.getit.utils.ApplicationClass.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendProductsService {
    private lateinit var recommendProductsView: RecommendProductsView

    fun setRecommendView(recommendProductsView: RecommendProductsView) {
        this.recommendProductsView = recommendProductsView
    }

    fun loadRecommendProducts(answerSet: RecommendAnswerSet) {
        val recommendService = retrofit.create(RecommendRetrofitInterface::class.java)
        recommendService.getRecommend(answerSet)
            .enqueue(object : Callback<RecommendResponse> {
                override fun onResponse(
                    call: Call<RecommendResponse>,
                    response: Response<RecommendResponse>
                ) {
                    val resp: RecommendResponse = response.body()!!
                    Log.d("테스트", resp.toString())

                    when (resp.code) {
                        1000 -> {
                            recommendProductsView.setRecommendProductByAnswer(resp.code, resp.result!!)
                        }

                    }
                }

                override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }
            }
            )

    }
}
