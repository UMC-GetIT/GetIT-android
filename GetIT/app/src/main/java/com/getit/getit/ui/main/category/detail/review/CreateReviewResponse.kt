package com.getit.getit.ui.main.category.detail.review

import com.google.gson.annotations.SerializedName

data class CreateReviewResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)