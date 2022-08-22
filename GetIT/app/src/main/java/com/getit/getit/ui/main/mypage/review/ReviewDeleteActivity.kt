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

class ReviewDeleteActivity: BaseActivity<ItemMypageProductReviewBinding>(
    ItemMypageProductReviewBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.reviewDeleteBtn.setOnClickListener {
            dialog()

        }
    }
        fun reviewdelete() {
            val deleteRetrofit = ApplicationClass.retrofit.create(ReviewApiService::class.java)
            deleteRetrofit.reviewDelete().enqueue(object : Callback<DeleteReview> {
                override fun onResponse(
                    call: Call<DeleteReview>,
                    response: Response<DeleteReview>
                ) {
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


        fun toast() {
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            //삭제 되었을시 리뷰 레이아웃 하나씩 빠지는 것도 구현해야 함

        }

        fun dialog() {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("삭제 후에는 복구할 수 없습니다. ")
            dialog.setMessage("정말 삭제하시겠습니까?")

            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                if (DialogInterface.BUTTON_POSITIVE == p1) {
                    reviewdelete()
                    toast()
                }
            }
            dialog.setPositiveButton("삭제", dialogLister)
            dialog.setNegativeButton("취소", null)
            dialog.show()

        }

    override fun initAfterBinding() {

    }
}