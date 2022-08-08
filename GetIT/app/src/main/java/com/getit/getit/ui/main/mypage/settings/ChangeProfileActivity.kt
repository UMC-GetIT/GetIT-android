package com.getit.getit.ui.main.mypage.settings

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.getit.getit.R
import com.getit.getit.databinding.ChangeProfileBinding

class ChangeProfileActivity : AppCompatActivity() {

    lateinit var binding: ChangeProfileBinding
    private var name: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initImageViewProfile()

        name = findViewById(R.id.change_name_btn)

        binding.nickname.setText(ApplicationClass.prefs.userId)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
                super.onBackPressed()
            }

        //완료하기 버튼
        binding.sucsses.setOnClickListener {
            ApplicationClass.prefs.userId = binding.nickname.text.toString()
            Toast.makeText(this, "수정완료되었습니다", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
    }

    // 3.툴바 메뉴 버튼을 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_setting_toolbar_profile, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }


    /*
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

    //프로필 사진 바꾸기
    private fun initImageViewProfile() {
        binding.circleImage.setOnClickListener {
            when {
                // 갤러리 접근 권한이 있는 경우
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                -> {
                    navigateGallery()
                }

                // 갤러리 접근 권한이 없는 경우 & 교육용 팝업을 보여줘야 하는 경우
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                -> {
                    showPermissionContextPopup()
                }

                // 권한 요청 하기(requestPermissions) -> 갤러리 접근(onRequestPermissionResult)
                else -> requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            }

        }
    }

    // 권한 요청 승인 이후 실행되는 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1000 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    navigateGallery()
                else
                    Toast.makeText(this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                //
            }
        }
    }

    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        // 가져올 컨텐츠들 중에서 Image 만을 가져온다.
        intent.type = "image/*"
        // 갤러리에서 이미지를 선택한 후, 프로필 이미지뷰를 수정하기 위해 갤러리에서 수행한 값을 받아오는 startActivityForeResult를 사용한다.
        startActivityForResult(intent, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 예외처리
        if (resultCode != Activity.RESULT_OK)
            return

        when (requestCode) {
            // 2000: 이미지 컨텐츠를 가져오는 액티비티를 수행한 후 실행되는 Activity 일 때만 수행하기 위해서
            2000 -> {
                val selectedImageUri: Uri? = data?.data
                if (selectedImageUri != null) {
                    binding.circleImage.setImageURI(selectedImageUri)
                } else {
                    Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("프로필 이미지를 바꾸기 위해서는 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }

    //sharedPreference로 데이터 저장

    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
    override fun onResume() {
        super.onResume()

        // Fetching the stored data
        // from the SharedPreference
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val s1 = sh.getString("name", "")
        val a = sh.getInt("age", 0)

        // Setting the fetched data
        // in the EditTexts
        name!!.setText(s1)

    }
    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored
    override fun onPause() {
        super.onPause()

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name!!.text.toString())
        myEdit.apply()
    }
}