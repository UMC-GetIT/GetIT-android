package com.getit.getit.ui.main.searchproduct

import com.google.gson.annotations.SerializedName

data class SearchProductResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<SearchProductResult>
)
data class SearchProductResult(
    @SerializedName("title") val title: String,
    @SerializedName("productId") val productId: String,
    @SerializedName("image") val image: String,
    @SerializedName("lprice") val lprice : Int
)