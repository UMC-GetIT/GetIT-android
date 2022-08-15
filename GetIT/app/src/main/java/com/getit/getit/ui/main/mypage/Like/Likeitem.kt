package com.getit.getit.ui.main.mypage.Like

import com.google.gson.annotations.SerializedName

data class Likeitem (
        @SerializedName("code") val code : Int,
        @SerializedName("isSuccess") val isSuccess:String,
        @SerializedName("message") val message:String,
        @SerializedName("result") val result: List<Result>
    ) {
    data class Result(
        @SerializedName("userIdx") val userIdx : Int,
        @SerializedName("productIdx") val productIdx : Int,
        @SerializedName("name") val name: String,

    )
}