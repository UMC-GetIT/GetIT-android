package com.getit.getit.ui.main.mypage.settings.changeProfile.name

import com.getit.getit.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.PATCH

interface ChangenameApi {
    @PATCH("/users/profileNickName")

    fun newnickname(@Body change : newname):Call<responsename>
}