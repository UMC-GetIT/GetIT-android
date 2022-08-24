package com.getit.getit.ui.main.home.recommend

import android.view.MenuItem
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


        var products = ArrayList<NameImagePriceProduct>()

        products.apply {
            add(NameImagePriceProduct("1","LG 그램 360","1,898,000원", R.drawable.gram_360))
            add(NameImagePriceProduct("2","13형 MacBook Pro","1,960,000원", R.drawable.macbook))
            add(NameImagePriceProduct("3","삼성전자 갤럭시북 프로", "1,499,000원", R.drawable.samsung))
        }

        val recommendResultRVAdapter = this?.let { RecommendResultRVAdapter(products, it) }
        binding.recommendProductsRv.adapter = recommendResultRVAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        binding.recommendProductsRv.layoutManager = linearLayoutManager
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}