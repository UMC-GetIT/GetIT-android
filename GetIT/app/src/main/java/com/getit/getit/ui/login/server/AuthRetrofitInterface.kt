package com.getit.getit.ui.login.server

import com.getit.getit.data.User
import com.getit.getit.ui.login.data.Tokens
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/users/sign-in")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/users/login")
    fun login(@Body user: User): Call<AuthResponse>

    @POST("/users/reissue")
    fun autoLogin(@Body tokens : Tokens) : Call<AuthResponse>

}