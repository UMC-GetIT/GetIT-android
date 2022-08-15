package com.getit.getit.ui.main.mypage.settings

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.FragmentSettingsBinding
import com.getit.getit.ui.login.LoginActivity


class settingActivity : AppCompatActivity() {
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

        //계정 변경
        binding.privacylinear.setOnClickListener {
            val intent = Intent(this, ChangePrivacyActivity::class.java)
            startActivity(intent)
        }

        //개발자 정보
        binding.developerlinear.setOnClickListener {
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
                //ApplicationClass.prefs.LoginActivity.email.remove("email") // 여기서 Shared Preference 를 remove 한다!
                //ApplicationClass.prefs.password.remove("password")
                //MyApplication.prefs.edit.commit() // SP 삭제되는 것을 확인
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_NEGATIVE -> toast()
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
            // dialog.setIcon 추후에 아이콘 삽입

            fun toast() {
                Toast.makeText(this, "회원탈퇴 되었습니다.", Toast.LENGTH_SHORT).show()
                //MyApplication.prefs.edit.remove("email") // 여기서 Shared Preference 를 remove 한다!
                //MyApplication.prefs.edit.remove("password")
                //MyApplication.prefs.edit.commit() // SP 삭제되는 것을 확인
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_NEGATIVE -> toast()
                }
            }
            dialog.setPositiveButton("탈퇴하기", dialogLister)
            dialog.setNegativeButton("취소", null)
            dialog.show()
        }


        // 1:1 문의
        binding.onetoonelinear.setOnClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto","heekyoung2000@naver.com",null))
                startActivity(Intent.createChooser(emailIntent,"Send email...."))
        }
        }


    }
