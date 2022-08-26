package com.getit.getit.ui.main.mypage.settings.changeProfile

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface ChangeProfileApi {
    @Multipart//파일이나 이미지 업로드 할때 사용
    @POST("/users/profileImg")

    fun changeprofile(
        @Part("profileImg") image: MultipartBody.Part,
        @Part("_method") method: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(), "PATCH")
    ): Call<profile>


}