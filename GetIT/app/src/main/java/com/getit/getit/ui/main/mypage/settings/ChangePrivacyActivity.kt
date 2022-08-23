package com.getit.getit.ui.main.mypage.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.getit.getit.databinding.SettingChangePrivacyBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.mypage.settings.changeprivacy.newpwd
import com.getit.getit.ui.main.mypage.settings.changeprivacy.PwdRetrofit


class ChangePrivacyActivity : BaseActivity<SettingChangePrivacyBinding>(SettingChangePrivacyBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.orginPassword.addTextChangedListener(textWatcher)
        binding.changeNewPassword.addTextChangedListener(textWatcher)
        binding.againPassword.addTextChangedListener(textWatcher)



        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }
1


//완료하기 버튼
        binding.sucsses.setOnClickListener {
            changepwdData()
            Toast.makeText(this, "수정완료되었습니다", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
        }
    private fun getpwd():newpwd{
        val currentpwd:String =binding.orginPassword.getText().toString().trim()
        val newpwd:String =binding.changeNewPassword.getText().toString().trim()

        if(currentpwd.isEmpty()){
            Toast.makeText(this, "현재 비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
        if(newpwd.isEmpty()){
            Toast.makeText(this,"새로운 비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
        }
        if(currentpwd==newpwd){
            Toast.makeText(this,"이전 비밀번호와 같습니다. 다시 입력해주세요",Toast.LENGTH_SHORT).show()
        }


        return newpwd(currentpwd,newpwd)

    }

        private fun changepwdData(){
            val passwordData = PwdRetrofit()
            passwordData.changeData(getpwd())


    }

    private val textWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.d("테스트","실행전?" + p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    override fun initAfterBinding() {
    }
}
