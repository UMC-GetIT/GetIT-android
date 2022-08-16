package com.getit.getit.ui.main.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.getit.getit.R
import com.getit.getit.databinding.ActivityRecommendBinding
import com.getit.getit.databinding.ActivitySignupBinding
import com.getit.getit.ui.BaseActivity

class RecommendActivity : BaseActivity<ActivityRecommendBinding>(ActivityRecommendBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);
    }

    override fun initAfterBinding() {
    }

    override fun onResume() {
        super.onResume()
        showActionBar() //지난번에 넣은 코드에서 이 부분만 추가
        setActionBarTitle("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}