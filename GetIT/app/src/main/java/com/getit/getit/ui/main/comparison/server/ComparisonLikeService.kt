package com.getit.getit.ui.main.comparison.server

import android.util.Log
import com.getit.getit.ui.main.comparison.ComparisonView
import com.getit.getit.ui.main.home.HomeView
import com.getit.getit.utils.ApplicationClass.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComparisonLikeService {
    private lateinit var comparisonView: ComparisonView

    fun setComparisonView(comparisonView: ComparisonView) {
        this.comparisonView = comparisonView
    }

    fun loadLikedProductsByKind(kind: String) {
        val comparisonService = retrofit.create(ComparisonLikeInterface::class.java)
        comparisonService.getLikedProducts(kind)
            .enqueue(object : Callback<ComparisonLikeResponse> {
                override fun onResponse(
                    call: Call<ComparisonLikeResponse>,
                    response: Response<ComparisonLikeResponse>
                ) {
                    Log.d("테스트", response.toString())

                    val resp: ComparisonLikeResponse = response.body()!!

                    when (resp.code) {
                        1000 -> {
                            comparisonView.loadLikedProducts(resp.code, resp.result!!)
                        }

                    }
                }

                override fun onFailure(call: Call<ComparisonLikeResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }
}