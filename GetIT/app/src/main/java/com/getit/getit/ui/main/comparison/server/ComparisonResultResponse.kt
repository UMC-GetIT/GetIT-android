package com.getit.getit.ui.main.comparison.server

import com.google.gson.annotations.SerializedName


data class ComparisonResultResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<ComparisonResultListResult>

)

data class ComparisonResultListResult(
    @SerializedName("productIdx") val productIdx: String,
    @SerializedName("name") val name : String,
    @SerializedName("link") val link : String,
    @SerializedName("photolist") val imgurl : List<String>,
    @SerializedName("brand") val brand : String,
    @SerializedName("date") val date : String,
    @SerializedName("cpu") val cpu : String,
    @SerializedName("cpurate") val cpurate : String,
    @SerializedName("core") val core : String,
    @SerializedName("size") val size : String,
    @SerializedName("ram") val ram : String,
    @SerializedName("weight") val weight : String,
    @SerializedName("lprice") val price : String,
    //@SerializedName("type") val type : String,
    @SerializedName("innermemory") val innermemory : String,
    @SerializedName("communication") val communication : String,
    @SerializedName("os") val os : String,
    @SerializedName("ssd") val ssd : String,
    @SerializedName("hdd") val hdd : String,
    @SerializedName("output") val output : String,
    @SerializedName("terminal") val terminal : String
)
