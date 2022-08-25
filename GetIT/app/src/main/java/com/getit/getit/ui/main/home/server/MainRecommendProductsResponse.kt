package com.getit.getit.ui.main.home.server

import com.google.gson.annotations.SerializedName


data class MainRecommendProductsResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: MainRecommendResult?

)

data class MainRecommendResult(
    @SerializedName("topic") val topic: String,
    @SerializedName("products") val products: List<MainRecommendProducts>,
)

data class MainRecommendProducts(
    @SerializedName("imgUrl") val imageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("lprice") val lprice: String,
    @SerializedName("productId") val productId: String
)