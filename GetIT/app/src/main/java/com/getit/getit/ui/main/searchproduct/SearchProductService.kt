package com.getit.getit.ui.main.searchproduct

import android.util.Log
import com.getit.getit.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchProductService {
    private lateinit var searchProductView: SearchProductView

    fun setSearchProductView(searchProductView: SearchProductView){
        this.searchProductView = searchProductView
    }

    fun getSearchProducts(keyword: String){
        val seachProductService = getRetrofit().create(SearchProductRetrofitInterface::class.java)

        searchProductView.onGetSearchProductLoading()

        seachProductService.getSearchProducts(keyword).enqueue(object: Callback<SearchProductResponse> {
            override fun onResponse(call: Call<SearchProductResponse>, response: Response<SearchProductResponse>) {
                if(response.isSuccessful && response.code() == 200) {
                    val searchProductResponse: SearchProductResponse = response.body()!!
//                    Log.d("SEARCH/SUCCESS", searchProductResponse.toString())

                    when (val code = searchProductResponse.code){
                        1000 -> searchProductView.onGetSearchProductSuccess(code, searchProductResponse.result)
                        else -> searchProductView.onGetSearchProductFailure(code, searchProductResponse.message)
                    }
                }
            }

            override fun onFailure(call: Call<SearchProductResponse>, t: Throwable) {
                Log.d("SEARCH/FAILURE", t.message.toString())
                searchProductView.onGetSearchProductFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
        Log.d("SEARCH", "HELLO")
    }
}