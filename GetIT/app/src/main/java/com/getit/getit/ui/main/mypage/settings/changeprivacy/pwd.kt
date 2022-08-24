package com.getit.getit.ui.main.mypage.settings.changeprivacy

import com.google.gson.annotations.SerializedName

data class pwd (
        @SerializedName("code") val code: Int,
        @SerializedName("isSuccess") val isSuccess: String,
        @SerializedName("message") val message: String,
        @SerializedName("result") val result: String
)