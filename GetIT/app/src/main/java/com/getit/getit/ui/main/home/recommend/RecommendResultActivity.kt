package com.getit.getit.ui.main.home.recommend

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.getit.getit.R
import com.getit.getit.databinding.ActivityRecommendResultBinding
import com.getit.getit.ui.BaseActivity

class RecommendResultActivity  : BaseActivity<ActivityRecommendResultBinding>(ActivityRecommendResultBinding::inflate){
    override fun initAfterBinding() {
        val toolbar : Toolbar = binding.toolbar
        toolbar.title = "추천 결과"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}