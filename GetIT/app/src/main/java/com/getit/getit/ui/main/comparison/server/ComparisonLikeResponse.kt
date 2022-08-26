package com.getit.getit.ui.main.comparison.server

import com.google.gson.annotations.SerializedName


data class ComparisonLikeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ComparisonLikeResult?

)

data class ComparisonLikeResult(
    @SerializedName("likeProduct") val products: List<ComparisonLikeProducts>,
)

data class ComparisonLikeProducts(
    @SerializedName("name") val name: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("productId") val productId: String
)