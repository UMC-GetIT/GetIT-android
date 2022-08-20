package com.getit.getit.ui.main.category

import android.util.Log
import com.getit.getit.data.Category
import com.getit.getit.ui.main.searchproduct.RecommendResponse
import com.getit.getit.ui.main.searchproduct.RecommendView
import com.getit.getit.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService {
    private lateinit var categorySearchView: CategorySearchView
    private lateinit var recommendView: RecommendView

    fun setSearchView(categorySearchView: CategorySearchView){
        this.categorySearchView = categorySearchView
    }

    fun setRecommendView(recommendView: RecommendView) {
        this.recommendView = recommendView
    }

    fun getCategory(type: String, requirement: String){
        val categoryService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        categorySearchView.onGetCategoryLoading()

        categoryService.getCategory(type, requirement).enqueue(object: Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                Log.d("TEST", "response 성공")
                Log.d("TEST", response.toString())
                if(response.isSuccessful && response.code() == 200) { // <- response code = 400
                    val categoryResponse: CategoryResponse = response.body()!!
                    Log.d("CATEGORY-RESPONSE/SUCCESS", categoryResponse.toString())

                    when(val code = categoryResponse.code){
                        1000 -> categorySearchView.onGetCategorySuccess(code, categoryResponse.result)
                        else -> categorySearchView.onGetCategoryFailure(code, categoryResponse.message)
                    }
                }
            }
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.d("TEST", "response 실패")
                Log.d("CATEGORY-RESPONSE/FAILURE", t.message.toString())
                categorySearchView.onGetCategoryFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

        Log.d("CATEGORY", "HELLO")
    }

    fun getRecommend(){
        val recommendService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        recommendService.getRecommend().enqueue(object: Callback<RecommendResponse>{
            override fun onResponse(call: Call<RecommendResponse>, response: Response<RecommendResponse>) {
                if(response.isSuccessful && response.code() == 200) {
                    val recommendResponse: RecommendResponse = response.body()!!

                    when (val code = recommendResponse.code){
                        1000 -> recommendView.onGetRecommendSuccess(code, recommendResponse.result)
                        else -> recommendView.onGetRecommendFailure(code, recommendResponse.message)
                    }
                }
            }
            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                Log.d("RECOMMEND/FAILURE", t.message.toString())
            }
        })
        Log.d("RECOMMEND", "HELLO")
    }
}