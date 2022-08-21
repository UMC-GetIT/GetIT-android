package com.getit.getit.ui.main.mypage.settings.changeprivacy

import com.google.gson.annotations.SerializedName

class newpwd (
        @SerializedName("password")val currentpwd:String,
        @SerializedName("newPassword") val newpwd:String
)