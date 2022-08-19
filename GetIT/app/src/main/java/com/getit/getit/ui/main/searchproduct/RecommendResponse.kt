package com.getit.getit.ui.main.searchproduct

import com.google.gson.annotations.SerializedName

data class RecommendResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<RecommendResult>
    )

data class RecommendResult(
    @SerializedName("keyword") val keyword: String
)