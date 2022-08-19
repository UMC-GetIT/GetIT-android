package com.getit.getit.ui.main.home.recommend

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.ActivityRecommendBinding
import com.getit.getit.databinding.ActivitySignupBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.home.TermRVAdapter
import com.getit.getit.ui.main.home.data.ItTermIcon

class RecommendActivity : BaseActivity<ActivityRecommendBinding>(ActivityRecommendBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);

        binding.desktopRecommendIb.setOnClickListener{
            startActivity(Intent(this, RecommendQuestionActivity::class.java))
        }


    }

    override fun initAfterBinding() {

    }

    override fun onResume() {
        super.onResume()
        showActionBar()
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