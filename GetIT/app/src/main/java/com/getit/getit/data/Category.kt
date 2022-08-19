package com.getit.getit.data

import com.google.gson.annotations.SerializedName


data class Category(
    @SerializedName(value = "type") val type: String,
    @SerializedName(value = "requirement") val requirement: String,
)

