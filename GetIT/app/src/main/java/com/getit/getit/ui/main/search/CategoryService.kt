package com.getit.getit.ui.main.search

import android.util.Log
import com.getit.getit.data.Category
import com.getit.getit.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService {
    private lateinit var searchView: SearchView

    fun setSearchView(searchView: SearchView){
        this.searchView = searchView
    }

    fun getCategory(category: Category){
        val categoryService = getRetrofit().create(CategoryRetrofitInterface::class.java)

        searchView.onGetCategoryLoading()

        categoryService.getCategory(category).enqueue(object: Callback<CategoryResponse>{
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if(response.isSuccessful && response.code() == 1000) {
                    val categoryResponse: CategoryResponse = response.body()!!

                    Log.d("CATEGORY-RESPONSE/SUCCESS", categoryResponse.toString())

                    when(val code = categoryResponse.code){
                        1000 -> searchView.onGetCategorySuccess(code, categoryResponse.result)
                        else -> searchView.onGetCategoryFailure(code, categoryResponse.message)
                    }
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                searchView.onGetCategoryFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }
}