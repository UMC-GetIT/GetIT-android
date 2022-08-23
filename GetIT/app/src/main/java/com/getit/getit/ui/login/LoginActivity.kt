package com.getit.getit.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.getit.getit.ui.login.server.AuthService
import com.getit.getit.ui.login.server.Result
import com.getit.getit.data.User
import com.getit.getit.databinding.ActivityLoginBinding
import com.getit.getit.databinding.DialogLoadingBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.login.data.Tokens
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.utils.ApplicationClass
import com.getit.getit.utils.LoadingDialog
import java.util.regex.Pattern

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView {

    private var lastTimeBackPressed: Long = 0
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            login();
        }

        binding.loginJoinTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        if(getJwt() != null){
            loadingDialog.show();
            autoLoginMedium()

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

        val emailPattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!(emailPattern.matcher(binding.loginIdEt.text.toString().trim()).matches())) {
            Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val email: String = binding.loginIdEt.text.toString()
        val pwd: String = binding.loginPasswordEt.text.toString()


        loadingDialog.show();
        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(email, pwd, ""))
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        saveJwt(result.accessToken, result.refreshToken)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        loadingDialog.hide();
    }

    override fun onLoginFailure() {
        Toast.makeText(this, "회원 정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        loadingDialog.hide()
    }

    override fun onServerFailure() {
        Toast.makeText(this, "알 수 없는 오류, 나중에 다시 시도하세요.", Toast.LENGTH_SHORT).show()
        loadingDialog.hide()
    }
    override fun onAutoLoginFailure() {
        loadingDialog.hide()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 2000){
            finishAffinity()
            return
        }
        Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
        lastTimeBackPressed= System.currentTimeMillis();
    }

    override fun initAfterBinding() {
        loadingDialog = LoadingDialog(this);
    }

    fun autoLoginMedium(){
        val authService = AuthService()
        authService.setLoginView(this)

        Log.d("테스트 리프", ApplicationClass.mSharedPreferences.getString(ApplicationClass.X_REFRESH_TOKEN, null).toString())
        Log.d("테스트 엑세", getJwt().toString())
        authService.autoLogin(Tokens(getJwt().toString(),
            ApplicationClass.mSharedPreferences.getString(ApplicationClass.X_REFRESH_TOKEN, null).toString()))
    }
}