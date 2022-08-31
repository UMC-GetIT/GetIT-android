package com.getit.getit.ui.main.mypage.review

import com.google.gson.annotations.SerializedName

data class ReviewProducts(

    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: List<result>,


)

data class result(
    @SerializedName("productName")
    val productname: String,
    @SerializedName("review")
    val review : String,
    @SerializedName("productIdx")
    val productIdx : String,
    @SerializedName("reviewIdx")
    val reviewIdx :Int,
    @SerializedName("profileIngUrl")
    val reviewImg:String
)