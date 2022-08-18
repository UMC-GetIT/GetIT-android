package com.getit.getit.ui.login

import android.R
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.toColor
import androidx.core.widget.doOnTextChanged
import com.getit.getit.AuthService
import com.getit.getit.R.*
import com.getit.getit.Result
import com.getit.getit.data.User
import com.getit.getit.databinding.ActivitySignupBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.MainActivity
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate), SignUpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            signUp()
        }


        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(drawable.ic_close);




        binding.signUpIdEt.addTextChangedListener(textWatcher)
        binding.signUpNicknameEt.addTextChangedListener(textWatcher)
        binding.signUpPasswordEt.addTextChangedListener(textWatcher)
        binding.signUpPasswordCheckEt.addTextChangedListener(textWatcher)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        super.onResume()
        showActionBar() //지난번에 넣은 코드에서 이 부분만 추가
        setActionBarTitle("")
    }

    private fun getUser(): User {
        val email: String = binding.signUpIdEt.text.toString().trim();
        val pwd: String = binding.signUpPasswordEt.text.toString().trim()
        val name: String = binding.signUpNicknameEt.text.toString().trim()

        return User(email, pwd, name)
    }

    private fun signUp() {

        binding.signUpEmailErrorTv.visibility = View.GONE
        binding.signUpNicknameErrorTv.visibility = View.GONE
        binding.signUpPasswordErrorTv.visibility = View.GONE
        binding.signUpPasswordCheckErrorTv.visibility = View.GONE

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!(pattern.matcher(binding.signUpIdEt.text.toString().trim()).matches())) {
            Toast.makeText(this, binding.signUpIdEt.text.toString() + "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpIdEt.text.toString().isEmpty()) {
            //3. 빈 텍스트로 들어가기 때문에 이 조건문에 걸림,
                //하지만 여기서부터 setText는 안되는데,(뷰에 안녕 글자가 보이지 않음) 아래 토스트 메세지는 잘 뜸
                    //아예 addTextChangedListener도 전혀 먹지 않음
            Toast.makeText(this, "이메일이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpNicknameEt.text.toString().isEmpty()){
            Toast.makeText(this, "이름이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            binding.signUpPasswordCheckErrorTv.visibility = View.VISIBLE
            return
        }


        Log.d("제발", "실행되나?")
        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())
    }

    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onSignUpSuccess(code: Int, result: Result) {
        saveJwt(result.jwt)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSignUpFailure(code: Int) {
        when(code){
            2001 -> {
                binding.signUpEmailErrorTv.visibility = View.VISIBLE
                //binding.signUpNicknameErrorTv.visibility = View.GONE
            }
            2000 -> {
                //binding.signUpEmailErrorTv.visibility = View.GONE
                binding.signUpNicknameErrorTv.visibility = View.VISIBLE
            }
        }
    }

    override fun initAfterBinding() {

    }
    private fun enableSignUpButton(){
        if (!checkExistEmpty()) {
            Log.d("제발", "실행됐나유")
            binding.signUpSignUpBtn.isEnabled = true
            binding.signUpSignUpBtn.setBackgroundColor(com.getit.getit.R.color.primary.toInt())
        }
    }

    private val textWatcher = object:TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.e("제발","실행전?" + p0.toString())

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.e("제발","실행전?" + p0.toString())
            enableSignUpButton()
        }

        override fun afterTextChanged(p0: Editable?) {
            Log.e("제발","실행 후?" + p0.toString())
        }

    }

    private fun checkExistEmpty() : Boolean {
        return binding.signUpIdEt.text.toString().isEmpty() || binding.signUpNicknameEt.text.toString().isEmpty()
                ||binding.signUpPasswordEt.text.toString().isEmpty() || binding.signUpPasswordEt.text.toString().isEmpty()
    }

}
