package com.getit.getit.ui.main.mypage.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getit.getit.databinding.ChangePrivacyBinding


class ChangePrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ChangePrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }

        //완료하기 버튼
        binding.sucsses.setOnClickListener {
            Toast.makeText(this, "수정완료되었습니다", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
    }

    /* // 3.툴바 메뉴 버튼을 설정
     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_setting_toolbar_profile, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
         return true
     }

     // 4.툴바 메뉴 버튼이 클릭 됐을 때 콜백
     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정한다.
         when(item!!.itemId){
             R.id.menu_finish->{ // 메뉴 버튼
                 Toast.makeText(this, "수정완료되었습니다", Toast.LENGTH_SHORT).show()
                 //Snackbar.make(toolbar,"Menu pressed", Snackbar.LENGTH_SHORT).show()
                 val intent = Intent(this, settingActivity::class.java)
                 startActivity(intent)
             }
             android.R.id.home->{ //뒤로가기 버튼
                 val intent = Intent(this, settingActivity::class.java)
                 startActivity(intent)
             }
         }
         return super.onOptionsItemSelected(item)
     }*/
}