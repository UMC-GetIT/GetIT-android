package com.getit.getit.data

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName(value = "productId") val productId: String,
    @SerializedName(value = "review") val email: String
)
