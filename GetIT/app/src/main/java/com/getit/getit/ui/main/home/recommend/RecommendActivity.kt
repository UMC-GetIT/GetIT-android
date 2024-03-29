package com.getit.getit.ui.main.home.recommend

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.getit.getit.R
import com.getit.getit.databinding.ActivityRecommendBinding
import com.getit.getit.ui.BaseActivity

class RecommendActivity : BaseActivity<ActivityRecommendBinding>(ActivityRecommendBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        moveToQuestionViewManage()

    }

    override fun initAfterBinding() {
        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);
    }

    private fun moveToQuestionViewManage() {
        binding.laptopRecommendIb.setOnClickListener {
            moveToQuestionView(1)
        }

        binding.phoneRecommendIb.setOnClickListener {
            moveToQuestionView(2)
        }

        binding.tabletRecommendIb.setOnClickListener {
            moveToQuestionView(3)
        }

        binding.speakerRecommendIb.setOnClickListener {
            moveToQuestionView(4)
        }

        binding.desktopRecommendIb.setOnClickListener {
            moveToQuestionView(5)
        }
    }

    private fun moveToQuestionView(productKind : Int) {
        intent = Intent(this, RecommendProductsQuestionActivity::class.java)
        intent.putExtra("productKind", productKind)
        startActivity(intent)
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