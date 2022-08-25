package com.getit.getit.ui.main.category.detail.Like

import com.google.gson.annotations.SerializedName

data class IsLikeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: IsLike
)

data class IsLike(
    @SerializedName("isLike") val isLike: Boolean
)
