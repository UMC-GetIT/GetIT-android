package com.getit.getit.ui.main.mypage.settings.changeProfile

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Part

class newprofile (
    @Part("profileImg") val image:MultipartBody.Part
        )