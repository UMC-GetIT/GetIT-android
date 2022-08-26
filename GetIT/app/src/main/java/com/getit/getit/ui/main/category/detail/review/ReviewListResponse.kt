package com.getit.getit.ui.main.category.detail.review

import com.google.gson.annotations.SerializedName

data class ReviewListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<ReviewListResult>
)

data class ReviewListResult(
    @SerializedName("reviewIdx") val reviewIdx: Int,
    @SerializedName("productIdx") val productIdx: Int,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("review") val review: String,
    @SerializedName("profileImgUrl") val profileImgUrl: String
)