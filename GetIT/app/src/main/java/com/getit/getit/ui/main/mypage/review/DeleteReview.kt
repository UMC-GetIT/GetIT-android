package com.getit.getit.ui.main.mypage.review

import com.google.gson.annotations.SerializedName

class DeleteReview(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: String,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)