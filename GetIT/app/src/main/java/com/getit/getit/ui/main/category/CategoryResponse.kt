package com.getit.getit.ui.main.category

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<CategoryResult>
)

data class CategoryResult(
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("productUrl") val productUrl: String
)
