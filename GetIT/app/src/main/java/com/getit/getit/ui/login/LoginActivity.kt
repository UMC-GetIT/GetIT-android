package com.getit.getit.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.AuthService
import com.getit.getit.Result
import com.getit.getit.data.User
import com.getit.getit.databinding.ActivityLoginBinding
import com.getit.getit.ui.main.MainActivity

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginSignInBtn.setOnClickListener {
            login();
        }

        binding.loginSignUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email: String = binding.loginIdEt.text.toString()
        val pwd: String = binding.loginPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(email, pwd, ""))
    }

    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        saveJwt(result.jwt)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailure() {
        Toast.makeText(this, "회원 정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onServerFailure() {
        Toast.makeText(this, "알 수 없는 오류, 나중에 다시 시도하세요.", Toast.LENGTH_SHORT).show()
    }
}