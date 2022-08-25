package com.getit.getit.ui.main.category.detail.Like

import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)