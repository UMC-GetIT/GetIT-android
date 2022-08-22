package com.getit.getit.ui.main.mypage.like

import com.google.gson.annotations.SerializedName


data class LikeProducts (
    val isSuccess : String,
    val code : Int,
    val message : String,
    val result: List<result>,
)

data class result(
    val likeProduct:likeProduct,
    val userIdx:Int
)
data class likeProduct(
val image : String,
val name: String
)
