package com.getit.getit.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String,
    @SerializedName(value = "nickname")val nickname: String
)
