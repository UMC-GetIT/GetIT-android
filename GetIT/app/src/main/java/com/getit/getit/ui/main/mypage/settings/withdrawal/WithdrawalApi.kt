package com.getit.getit.ui.main.mypage.settings.withdrawal

import retrofit2.Call
import retrofit2.http.DELETE

interface WithdrawalApi {
    @DELETE("/users/withdrawl")
    fun deleteuser():Call<Withdrawal>
}