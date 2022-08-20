package com.getit.getit.ui.login

import com.getit.getit.ui.main.home.server.Result

interface SignUpView {
    fun onSignUpSuccess(code: Int, result: Result)
    fun onSignUpFailure(code: Int)
}