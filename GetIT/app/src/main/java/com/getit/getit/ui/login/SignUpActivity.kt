package com.getit.getit.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.AuthService
import com.getit.getit.Result
import com.getit.getit.data.User
import com.getit.getit.databinding.ActivitySignupBinding
import com.getit.getit.ui.main.MainActivity
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(), SignUpView {
    lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            Log.d("진행 사항 보고", "버튼 클릭")
            signUp()
        }

        binding.signUpBackIv.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun getUser(): User {
        val email: String = binding.signUpIdEt.text.toString().trim();
        val pwd: String = binding.signUpPasswordEt.text.toString().trim()
        val name: String = binding.signUpNameEt.text.toString().trim()
        Log.d("진행 사항 보고", "get User " + email+" " + pwd +" " + name)

        return User(email, pwd, name)
    }

    private fun signUp() {
        Log.d("진행 사항 보고", "회원가입 시작")
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!(pattern.matcher(binding.signUpIdEt.text.toString().trim()
            ).matches())) {
            Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpNameEt.text.toString().isEmpty()){
            Toast.makeText(this, "이름이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }


        val authService = AuthService()
        authService.setSignUpView(this)
        Log.d("진행 사항 보고", "AuthService")

        authService.signUp(getUser())
    }

    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onSignUpSuccess(code: Int, result: Result) {
        Log.d("진행 사항 보고", "1000")
        saveJwt(result.jwt)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSignUpFailure(code: Int) {
        when(code){
            2001 -> {
                binding.signUpEmailErrorTv.visibility = View.VISIBLE
                binding.signUpNicknameErrorTv.visibility = View.GONE
            }
            2000 -> {
                binding.signUpEmailErrorTv.visibility = View.GONE
                binding.signUpNicknameErrorTv.visibility = View.VISIBLE
            }
        }
    }
}