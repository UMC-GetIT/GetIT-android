package com.getit.getit.ui.main.mypage.settings.changeProfile

import com.google.gson.annotations.SerializedName

class newprofile (
    @SerializedName("profileImg") val profileImg:String,
    @SerializedName("user") val nickname:String
        )