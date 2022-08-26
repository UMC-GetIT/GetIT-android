package com.getit.getit.ui.main.comparison.server

import android.util.Log
import com.getit.getit.ui.main.comparison.ComparisonAfterView
import com.getit.getit.utils.ApplicationClass.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComparisonResultService {
    private lateinit var comparisonAfterView: ComparisonAfterView

    fun setComparisonAfterView(comparisonAfterView: ComparisonAfterView) {
        this.comparisonAfterView = comparisonAfterView
    }

    fun loadComparisonResult(productIdx1 : String, productIdx2 : String) {
        val comparisonService = retrofit.create(ComparisonResultInterface::class.java)
        comparisonService.getComparisonResultProducts(productIdx1, productIdx2)
            .enqueue(object : Callback<ComparisonResultResponse> {
                override fun onResponse(
                    call: Call<ComparisonResultResponse>,
                    response: Response<ComparisonResultResponse>
                ) {
                    Log.d("테스트 비교결과", response.toString())

                    val resp: ComparisonResultResponse = response.body()!!

                    when (resp.code) {
                        1000 -> {
                            comparisonAfterView.loadComparisonResult(resp.code, resp.result!!)
                        }

                    }
                }

                override fun onFailure(call: Call<ComparisonResultResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }
}