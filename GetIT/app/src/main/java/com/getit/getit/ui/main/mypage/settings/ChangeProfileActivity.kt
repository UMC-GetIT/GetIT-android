package com.getit.getit.ui.main.mypage.settings

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.getit.getit.R
import com.getit.getit.databinding.ActivityLoginBinding
import com.getit.getit.databinding.SettingChangeProfileBinding
import com.getit.getit.ui.main.mypage.settings.changeProfile.ProfileRetrofit
import com.getit.getit.ui.main.mypage.settings.changeProfile.newprofile
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink

class ChangeProfileActivity : AppCompatActivity(){


    lateinit var binding: SettingChangeProfileBinding
    lateinit var _binding: ActivityLoginBinding
    lateinit var change_name: EditText
    lateinit var change_image: CircleImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initImageViewProfile()

        change_name = findViewById(R.id.change_name)


        //binding.nickname.setText(ApplicationClass.prefs.userId)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }

        binding.circleImage.setOnClickListener {
            initImageViewProfile()
        }

        //완료하기 버튼
        binding.sucsses.setOnClickListener {

            //changeProfileData()

            //ApplicationClass.prefs.userId = binding.nickname.text.toString()
            Toast.makeText(this, "수정완료되었습니다", Toast.LENGTH_SHORT).show()
            super.onBackPressed()

            //sharedPreferences 사용해서 텍스트 저장 성공!!!
            // Creating a shared pref object
            // with a file name "MySharedPref"
            // in private mode
            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("name", change_name!!.text.toString())
            myEdit.apply()
        }

    }

    //프로필 사진 바꾸기
    private fun initImageViewProfile() {


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
                    //uploadProfile(selectedImageUri)


                } else {
                    Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }

            }
            else -> {
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }

        }


    }

    /*private fun uploadProfile(selectedImageUri: Uri) {


        val requestImage = RequestBody.create(MediaType.parse(""),selectedImageUri)
        val body = MultipartBody.Part.createFormData("file",requestImage)
    }


    }
    private fun uploadProfile(selectedImageUri: Uri) {
        binding.circleImage.setImageURI(selectedImageUri)

        val requestImage = RequestBody.create(MediaType.parse(""),selectedImageUri)
        val body = MultipartBody.Part.createFormData("file",requestImage)
    }


    inner class BitmapRequestBody(private val bitmap: Bitmap):RequestBody(){
        override fun contentType(): MediaType = "image/jpeg".toMediaType()
        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG,99,sink.outputStream())
        }

    }*/


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
}



    private fun getnickname():newprofile{
       //val newImageUrl = change_image
        val newnickname:String = binding.changeName.getText().toString()

        return newprofile(newnickname)

    }

    private fun changeProfileData(){
        val profileData = ProfileRetrofit()
        profileData.changeData(getnickname())


    }


    //sharedPreference로 데이터 저장

    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
    /*override fun onResume() {
        super.onResume()





/* private fun getnickname():newprofile{
    //val newImageUrl = change_image
     val newnickname:String = binding.changeName.getText().toString()

    }*/
    /*private fun login() {
        if (_binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (_binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email: String = _binding.loginIdEt.text.toString()
        val pwd: String = _binding.loginPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(email, pwd, ""))


        //sharedPreference

    }

    override fun onLoginSuccess(code: Int, result: Result) {
        TODO("Not yet implemented")
    }

    override fun onLoginFailure() {
        TODO("Not yet implemented")
    }

    override fun onServerFailure() {
        TODO("Not yet implemented")
    }
    */
    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored
    /*override fun onPause() {
        super.onPause()

     return newprofile(newnickname)

 }

 /*private fun cngeProfileData(){
     val profileData = ProfileRetrofit()
     profileData.changeData(getnickname())


 }*/


 //sharedPreference로 데이터 저장

 // Fetch the stored data in onResume()
 // Because this is what will be called
 // when the app opens again
 /*override fun onResume() {
     super.onResume()

     // Fetching the stored data
     // from the SharedPreference
     val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
     val s1 = sh.getString("name", "")
     val a = sh.getInt("age", 0)

     // Setting the fetched data
     // in the EditTexts
     name!!.setText(s1)

 }*/
 /*private fun login() {
     if (_binding.loginIdEt.text.toString().isEmpty()) {
         Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
         return
     }

     if (_binding.loginPasswordEt.text.toString().isEmpty()) {
         Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
         return
     }

     val email: String = _binding.loginIdEt.text.toString()
     val pwd: String = _binding.loginPasswordEt.text.toString()

     val authService = AuthService()
     authService.setLoginView(this)

     authService.login(User(email, pwd, ""))


     //sharedPreference

 }

 override fun onLoginSuccess(code: Int, result: Result) {
     TODO("Not yet implemented")
 }

 override fun onLoginFailure() {
     TODO("Not yet implemented")
 }

 override fun onServerFailure() {
     TODO("Not yet implemented")
 }
 */
 // Store the data in the SharedPreference
 // in the onPause() method
 // When the user closes the application
 // onPause() will be called
 // and data will be stored
 /*override fun onPause() {
     super.onPause()

     // Creating a shared pref object
     // with a file name "MySharedPref"
     // in private mode
     val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
     val myEdit = sharedPreferences.edit()

     // write all the data entered by the user in SharedPreference and apply
     myEdit.putString("name", name!!.text.toString())
     myEdit.apply()
 }*/
}
 */