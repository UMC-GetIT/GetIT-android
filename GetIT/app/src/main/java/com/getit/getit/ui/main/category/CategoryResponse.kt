package com.getit.getit.ui.main.category

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: CategoryResult
)

data class CategoryResult(
    @SerializedName("products") val products: List<CategoryProducts>
)

data class CategoryProducts(
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("lprice") val lprice: String,
    @SerializedName("productId") val productId: String
)
