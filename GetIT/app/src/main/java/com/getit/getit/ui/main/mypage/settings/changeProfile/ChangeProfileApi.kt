package com.getit.getit.ui.main.mypage.settings.changeProfile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ChangeProfileApi {
    @Multipart//파일이나 이미지 업로드 할때 사용
    @PATCH("/users/profile")

    fun changeprofile(@Part data: String,
                      @Part file: MultipartBody.Part
    ): Call<profile>
    //MultipartBody.Part는 Bitmap 형태로 보냄


}