package com.getit.getit.ui.login

import com.getit.getit.ui.main.home.server.Result

interface LoginView {
    fun onLoginSuccess(code: Int, result : Result)
    fun onLoginFailure()
    fun onServerFailure()
}