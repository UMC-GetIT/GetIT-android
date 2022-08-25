package com.getit.getit.ui.main.mypage

import com.google.gson.annotations.SerializedName
data class UserInfo(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: String,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result?,
)


data class Result(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("email") val email: String,
    @SerializedName("nickName") val nickname: String,
    @SerializedName("role") val role: String,
    @SerializedName("likeProduct") val likeProduct:List<likeProduct>,
    @SerializedName("review") val review:List<Review>,

    //@SerializedName("image")
    //val image:String
)

data class likeProduct(

    @SerializedName("productIdx")
    val productIdx: Int,

    @SerializedName("image")
    val likeimage: String,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("name")
    val name: String,
    @SerializedName("lowestprice")
    val price:String,

    @SerializedName("productUrl")
    val productUrl: String

//brand이름이랑 name 이름이랑 따로 주는지 아니면 같이 주는지
    //productId, name이랑 뭐가 다른지
    //lowestprice가 필요한지

)

data class Review(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("review") val review: String,
    @SerializedName("reviewImgUrl") val reviewImgUrl: String,
    @SerializedName("image") val image: String,
    @SerializedName("reviewList") val reviewList:reviewList?
    )

data class reviewList(
    @SerializedName("productIdx") val productIdx: Int,
    @SerializedName("name") val name:String
)

