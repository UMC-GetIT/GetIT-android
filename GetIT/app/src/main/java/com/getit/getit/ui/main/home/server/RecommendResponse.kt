package com.getit.getit.ui.main.home.server

import com.google.gson.annotations.SerializedName


data class RecommendResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<RecommendResult>?

)

data class RecommendResult(
    @SerializedName("productImg") val imageUrl: String,
    @SerializedName("productName") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("productId") val productId: String
)