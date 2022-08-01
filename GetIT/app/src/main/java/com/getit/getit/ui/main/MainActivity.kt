package com.getit.getit.ui.main

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.getit.getit.R
import com.getit.getit.databinding.ActivityMainBinding
import com.getit.getit.ui.BaseActivity


class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var navHostFragment: NavHostFragment
    private var lastTimeBackPressed: Long = 0

    override fun initAfterBinding() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()

        binding.mainBottomNavigation.setupWithNavController(navController)

        // 툴바
        setSupportActionBar(binding.toolbar);
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false) //기본 제목을 없애줍니다.
        actionBar?.setDisplayHomeAsUpEnabled(true) //자동 뒤로가기
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 2000){
            finishAffinity()
            return
        }
        Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
        lastTimeBackPressed= System.currentTimeMillis();
    }

    // menu 파일 inflate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    // 아이템(검색 버튼)이 클릭되었을 때 -> 구현해야 함
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> startActivity(Intent(this, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    // 툴바 타이틀 변경 -> 프래그먼트에서 override하여 사용
    fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    // 툴바 쓴다
    fun showActionBar(){
        supportActionBar?.show()
    }

    // 툴바 안쓴다
    fun hideActionBar() {
        supportActionBar?.hide()
    }
}