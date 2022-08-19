package com.getit.getit.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.getit.getit.AuthService
import com.getit.getit.R
import com.getit.getit.Result
import com.getit.getit.data.User
import com.getit.getit.databinding.ActivitySignupBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.MainActivity
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate), SignUpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            signUp()
        }


        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);

        binding.signUpIdEt.addTextChangedListener(textWatcher)
        binding.signUpNicknameEt.addTextChangedListener(textWatcher)
        binding.signUpPasswordEt.addTextChangedListener(textWatcher)
        binding.signUpPasswordCheckEt.addTextChangedListener(textWatcher)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
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

        val emailPattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!(emailPattern.matcher(binding.signUpIdEt.text.toString().trim()).matches())) {
            Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpNicknameEt.text.toString().isEmpty()){
            Toast.makeText(this, "닉네임이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val passwordPattern : Pattern = Pattern.compile("""^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^+\-=])(?=\S+$).*$""")
        val passwordString : String = binding.signUpPasswordEt.text.toString().trim()
        Log.d("테스트", passwordString)
        Log.d("테스트", passwordPattern.matcher(passwordString).matches().toString())
        Log.d("테스트", (passwordString.length !in 8..16).toString())
        Log.d("테스트", passwordString.length.toString())
        if (!(passwordPattern.matcher(passwordString).matches())||
            passwordString.length !in 8..16
        ){
            binding.signUpPasswordErrorTv.visibility = View.VISIBLE
            return
        }

        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            binding.signUpPasswordCheckErrorTv.visibility = View.VISIBLE
            return
        }

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
            }
            2000 -> {
                binding.signUpNicknameErrorTv.visibility = View.VISIBLE
            }
        }
    }

    override fun initAfterBinding() {

    }
    private fun enableSignUpButton(){
        if (!checkExistEmpty()) {
            binding.signUpSignUpBtn.isEnabled = true
            binding.signUpSignUpBtn.setBackgroundColor(getColor(R.color.primary));
        }
        else{
            binding.signUpSignUpBtn.isEnabled = false
            binding.signUpSignUpBtn.setBackgroundColor(getColor(R.color.outline))
        }
    }

    private val textWatcher = object:TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.e("테스트","실행전?" + p0.toString())
            enableSignUpButton()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }


    private fun checkExistEmpty() : Boolean {
        return binding.signUpIdEt.text.toString().isEmpty() || binding.signUpNicknameEt.text.toString().isEmpty()
                ||binding.signUpPasswordEt.text.toString().isEmpty() || binding.signUpPasswordCheckErrorTv.text.toString().isEmpty()
    }

}
