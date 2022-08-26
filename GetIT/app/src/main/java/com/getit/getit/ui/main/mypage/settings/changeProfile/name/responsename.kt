package com.getit.getit.ui.main.mypage.settings.changeProfile.name

import com.google.gson.annotations.SerializedName

data class responsename (
        @SerializedName("code") val code: Int,
        @SerializedName("isSuccess") val isSuccess: String,
        @SerializedName("message") val message: String,
        @SerializedName("result") val result: String
)