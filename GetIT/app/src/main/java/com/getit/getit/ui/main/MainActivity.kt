package com.getit.getit.ui.main

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
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 2000){
            finishAffinity()
            return
        }
        Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
        lastTimeBackPressed= System.currentTimeMillis();
    }

}