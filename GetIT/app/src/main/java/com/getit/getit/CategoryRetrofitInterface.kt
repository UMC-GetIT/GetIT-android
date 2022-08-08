package com.getit.getit

import com.getit.getit.data.Category
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface CategoryRetrofitInterface {
    @GET("/products/category")
    fun getCategory(@Body category: Category): Call<CategoryResponse>

}