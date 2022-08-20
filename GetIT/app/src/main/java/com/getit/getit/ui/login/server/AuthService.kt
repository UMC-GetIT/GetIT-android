package com.getit.getit.ui.login.server

import android.util.Log
import com.getit.getit.data.User
import com.getit.getit.ui.login.LoginView
import com.getit.getit.ui.login.SignUpView
import com.getit.getit.ui.login.data.Tokens
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun signUp(user: User){
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.signUp(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("테스트", response.toString())
                val resp: AuthResponse = response.body()!!
                when(resp.code){
                    1000 -> {
                        Log.d("테스트", "진입성공 1000")
                        signUpView.onSignUpSuccess(resp.code, resp.result!!)
                    }
                    else -> {
                        Log.d("테스트", resp.code.toString())
                        signUpView.onSignUpFailure(resp.code)
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("테스트", t.message.toString())
            }

        })
    }

    fun login(user: User){
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.login(user)
            .enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("테스트", response.toString())
                Log.d("테스트", response.body().toString())
                val resp: AuthResponse = response.body()!!

                when(val code = resp.code){
                    1000 -> loginView.onLoginSuccess(code, resp.result!!)
                    2006,2007 -> loginView.onAutoLoginFailure()
                    else -> loginView.onServerFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE", t.message.toString())
            }

        })
    }

    fun autoLogin(tokens: Tokens){
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.autoLogin(tokens)
            .enqueue(object: Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    val resp: AuthResponse = response.body()!!

                    when(val code = resp.code){
                        1000 -> loginView.onLoginSuccess(code, resp.result!!)
                        else -> loginView.onAutoLoginFailure()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d("LOGIN/FAILURE", t.message.toString())
                }

            })
    }
}