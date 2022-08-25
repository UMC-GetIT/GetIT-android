package com.getit.getit.ui.main.home.recommend

import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.data.NameImagePriceProduct
import com.getit.getit.databinding.ActivityRecommendResultBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.home.data.ItTermIcon
import com.getit.getit.ui.main.home.itterm.TermRVAdapter

class RecommendResultActivity  : BaseActivity<ActivityRecommendResultBinding>(ActivityRecommendResultBinding::inflate){
    override fun initAfterBinding() {
        val toolbar : Toolbar = binding.toolbar
        toolbar.title = "추천 결과"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

        var products = intent.getSerializableExtra("products") as ArrayList<NameImagePriceProduct>

        if(products.size == 0){
            binding.sorryTv.visibility  = View.VISIBLE
        }
        else {

            val recommendResultRVAdapter = this?.let { RecommendResultRVAdapter(this, products, it) }
            binding.recommendProductsRv.adapter = recommendResultRVAdapter

            val linearLayoutManager = LinearLayoutManager(this)
            binding.recommendProductsRv.layoutManager = linearLayoutManager
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}