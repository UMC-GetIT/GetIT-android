package com.getit.getit.ui.main.mypage.review

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.getit.getit.databinding.ActivityMypageReviewListBinding
import com.getit.getit.databinding.ItemMypageProductReviewBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.login.LoginActivity
import com.getit.getit.ui.main.mypage.settings.withdrawal.Withdrawal
import com.getit.getit.ui.main.mypage.settings.withdrawal.WithdrawalApi
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*class ReviewDeleteDialog{
    fun reviewdelete() {
        val deleteRetrofit = ApplicationClass.retrofit.create(ReviewApiService::class.java)
        deleteRetrofit.reviewDelete().enqueue(object : Callback<DeleteReview> {
            override fun onResponse(
                call: Call<DeleteReview>,
                response: Response<DeleteReview>) {
                Log.d("테스트", response.body().toString())
                if (response.isSuccessful) {
                    Log.d("성공", response.body().toString())

                } else {
                    Log.d("실패", response.body().toString())
                }
            }

            override fun onFailure(call: Call<DeleteReview>, t: Throwable) {

            }
        })
    }

}*/

