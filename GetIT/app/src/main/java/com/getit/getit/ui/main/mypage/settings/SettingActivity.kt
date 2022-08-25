package com.getit.getit.ui.main.mypage.settings

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.FragmentSettingsBinding
import com.getit.getit.ui.login.LoginActivity
import com.getit.getit.ui.main.mypage.like.LikeProducts
import com.getit.getit.ui.main.mypage.settings.withdrawal.Withdrawal

import com.getit.getit.ui.main.mypage.settings.withdrawal.WithdrawalApi
import com.getit.getit.ui.splash.SplashActivity
import com.getit.getit.utils.ApplicationClass
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingActivity : AppCompatActivity() {
    lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }

        //프로필 변경
        binding.Profilelinear.setOnClickListener {
            val intent = Intent(this, ChangeProfileActivity::class.java)
            startActivity(intent)
        }

        //프로필 변경
        binding.profileImageBtn.setOnClickListener {
            val intent = Intent(this, ChangeProfileActivity::class.java)
            startActivity(intent)
        }

        //계정 변경
        binding.privacylinear.setOnClickListener {
            val intent = Intent(this, ChangePrivacyActivity::class.java)
            startActivity(intent)
        }

        //계정 변경
        binding.privacyImageBtn.setOnClickListener {
            val intent = Intent(this, ChangePrivacyActivity::class.java)
            startActivity(intent)
        }

        //개발자 정보
        binding.developerlinear.setOnClickListener {
            val intent = Intent(this, DeveloperInfoActivity::class.java)
            startActivity(intent)
        }

        //개발자 정보
        binding.developerImageBtn.setOnClickListener {
            val intent = Intent(this, DeveloperInfoActivity::class.java)
            startActivity(intent)
        }


        //로그아웃
        binding.logout.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃을 하시겠습니까?")
            dialog.setMessage("서비스를 이용해주셔서 감사합니다.")
            // dialog.setIcon 추후에 아이콘 삽입


            fun toast() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                ApplicationClass.mSharedPreferences.edit().clear()
                    .commit()// sharedpreferences에 저장되어있는 토큰 제거
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }


            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE -> toast()
                }
            }
            dialog.setPositiveButton("네", dialogLister)
            dialog.setNegativeButton("아니오", null)
            dialog.show()

        }

        //회원탈퇴
        binding.withdrawal.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("회원탈퇴를 하시겠습니까?")
            dialog.setMessage("탈퇴 시 본인 계정의 모든 기록이 삭제됩니다.")

            fun datadelete(){
            val deleteRetrofit = ApplicationClass.retrofit.create(WithdrawalApi::class.java)
            deleteRetrofit.deleteuser().enqueue(object : Callback<Withdrawal> {
                override fun onResponse(call: Call<Withdrawal>, response: Response<Withdrawal>) {
                    if (response.isSuccessful) {
                            Log.d("성공", response.body().toString())

                        } else {
                            Log.d("실패", response.body().toString())
                        }
                    }

                override fun onFailure(call: Call<Withdrawal>, t: Throwable) {

                }
            })
        }


            fun toast() {
                Toast.makeText(this, "회원탈퇴 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }


            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                if (DialogInterface.BUTTON_POSITIVE == p1) {
                    datadelete()
                    toast()
                }
            }
            dialog.setPositiveButton("탈퇴하기", dialogLister)
            dialog.setNegativeButton("취소", null)
            dialog.show()
        }




            // 1:1 문의
            binding.onetoonelinear.setOnClickListener {
                val emailIntent = Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", "heekyoung2000@naver.com", null)
                )
                startActivity(Intent.createChooser(emailIntent, "Send email...."))
            }

            binding.onetooneImageBtn.setOnClickListener {
                val emailIntent = Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", "heekyoung2000@naver.com", null)
                )
                startActivity(Intent.createChooser(emailIntent, "Send email...."))
            }
        }
    }





