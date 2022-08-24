package com.getit.getit.ui.main.mypage.settings.changeprivacy

import com.getit.getit.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.PATCH

interface ChangepwdApi {
    @PATCH("/users/pwd")

    fun newpwd(@Body change : newpwd):Call<pwd>
}