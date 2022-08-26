package com.getit.getit.ui.main.mypage.settings

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.getit.getit.R
import com.getit.getit.databinding.SettingChangeProfileBinding
import com.getit.getit.ui.main.mypage.MypageFragment
import com.getit.getit.ui.main.mypage.settings.changeProfile.ChangeProfileApi
import com.getit.getit.ui.main.mypage.settings.changeProfile.name.newname
import com.getit.getit.ui.main.mypage.settings.changeProfile.name.newnameRetrofit
import com.getit.getit.ui.main.mypage.settings.changeProfile.profile
import com.getit.getit.utils.ApplicationClass
import com.getit.getit.utils.ApplicationClass.Companion.TAG
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ChangeProfileActivity : AppCompatActivity() {


    lateinit var binding: SettingChangeProfileBinding
    lateinit var change_name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        change_name = findViewById(R.id.change_name)


        //binding.nickname.setText(ApplicationClass.prefs.userId)

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            super.onBackPressed()
        }

        binding.circleImage.setOnClickListener {
            selectGallery()
            //initImageViewProfile()
        }

        //완료하기 버튼
        binding.sucsses.setOnClickListener {

            changenameData()
            super.onBackPressed()

            //changeProfileData()

            //ApplicationClass.prefs.userId = binding.nickname.text.toString()
            Toast.makeText(this, "수정완료되었습니다", Toast.LENGTH_SHORT).show()

            val mFragmentManager = supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mFragment = MypageFragment()

            val mBundle = Bundle()
            mBundle.putString("name",change_name!!.text.toString())
            mFragment.arguments = mBundle
            mFragmentTransaction.add(R.id.mypage_layout,mFragment).commit()
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

    //닉네임 변경
    private fun getname(): newname {
        val newnickname: String = binding.changeName.getText().toString().trim()
        return newname(newnickname)

    }

    private fun changenameData() {
        val nameData = newnameRetrofit()
        nameData.changeData(getname())
    }

    //갤러리 권한 요청
    companion object {
        const val REVIEW_MIN_LENGTH = 10
        const val REQ_GALLERY = 1
    }

    //이미지를 결과값으로 받는 변수
    private val imageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageUri = result.data?.data
                imageUri?.let {
                    val imageFile = File(getRealPathFromURI(it))
                    val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
                    val body = MultipartBody.Part.createFormData("profileImg", imageFile.name, requestFile)

                    //이미지 불러오기
                    Glide.with(this)
                        .load(imageUri)
                        .into(binding.circleImage)

                    Log.d(TAG,imageFile.name)



                    sendImage(body)


                }
            }
        }

    //이미지 실제 경로 반환
    private fun getRealPathFromURI(uri: Uri?): String {
        var columIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri!!, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            columIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        val result = cursor.getString(columIndex)
        cursor.close()
        return result

    }

    //갤러리를 부르는 메서드
    private fun selectGallery() {
        val writePermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        //권한 확인
        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
            //권한 요청
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ), REQ_GALLERY
            )

        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*"
            )
            imageResult.launch(intent)
        }
    }

    fun sendImage(image: MultipartBody.Part){


        val newProfileRetrofit = ApplicationClass.retrofit.create(ChangeProfileApi::class.java)
        val call = newProfileRetrofit.changeprofile(image)!!
        call.enqueue(object : Callback<profile> {
            override fun onResponse(call: Call<profile>, response: Response<profile>) {
                if (response.isSuccessful) {
                    Log.d("성공", response.body().toString())


                } else {
                    Log.d("실패", response.body().toString())

                }
            }

            override fun onFailure(call: Call<profile>, t: Throwable) {
                Log.e("error : ", t.message.toString())

            }
        })
    }


}


//프로필 사진 바꾸기
    /*private fun initImageViewProfile() {
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
                val imgFile = File(getRealPathFromURI(selectedImageUri))
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
    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("프로필 이미지를 바꾸기 위해서는 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }*/






   /* private fun uploadProfile(selectedImageUri: Uri) {


            val requestImage = RequestBody.create("image/jpeg".toMediaTypeOrNull(), selectedImageUri)
            val body = MultipartBody.Part.createFormData("file", requestImage)


            fun changeData() {
                val newProfileRetrofit =
                    ApplicationClass.retrofit.create(ChangeProfileApi::class.java)
                newProfileRetrofit.changeprofile("", body).enqueue(object : Callback<profile> {
                    override fun onResponse(call: Call<profile>, response: Response<profile>) {
                        if (response.isSuccessful) {
                            Log.d("성공", response.body().toString())


                        } else {
                            Log.d("실패", response.body().toString())

                        }
                    }

                    override fun onFailure(call: Call<profile>, t: Throwable) {
                        Log.e("error : ", t.message.toString())

                    }
                })
            }
        }
/*

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







    /*private fun getnickname():newprofile{
       //val newImageUrl = change_image
        val newnickname:String = binding.changeName.getText().toString()

        return newprofile(newnickname)

    }*/

    /*private fun changeProfileData(){
        val profileData = ProfileRetrofit()
        profileData.changeData(getnickname())


    }*/


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