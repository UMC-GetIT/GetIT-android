package com.getit.getit.ui.login

import com.getit.getit.Result

interface LoginView {
    fun onLoginSuccess(code: Int, result : Result)
    fun onLoginFailure()
}