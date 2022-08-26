package com.getit.getit.ui.main.mypage.like

import com.google.gson.annotations.SerializedName


data class LikeProducts (
    val isSuccess : String,
    val code : Int,
    val message : String,
    val result: result?,
)

data class result(
    val likeProduct:List<likeProduct>,
    val userIdx:Int
)
data class likeProduct(
    val productIdx : Int,
    val name: String,
    val brand : String,
    val type : String,
    val image : String,
    val lowestprice: String,
    val productId: String,
    val productUrl: String

)
