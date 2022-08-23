package com.getit.getit.ui.main.comparison

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.ActivityComparisonAfterBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.comparison.data.CompareAnswer
import com.getit.getit.ui.main.comparison.data.ProductImageName

class CompareAfterActivity  : BaseActivity<ActivityComparisonAfterBinding>(ActivityComparisonAfterBinding::inflate){
    override fun initAfterBinding() {
        val toolbar : Toolbar = binding.toolbar
        toolbar.title = "비교 결과"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

        setResultProducts()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setResultProducts() {
        var compareAnswers = ArrayList<CompareAnswer>()
        // 더미데이터
        compareAnswers.apply {
            add(CompareAnswer("가격", "1,626,200", "1,626,200"))
            add(CompareAnswer("브랜드", "LG전자", "Apple"))
            add(CompareAnswer("CPU", "11세대 인텔 코어\ni5-1135 G7", "11세대 인텔 코어\ni5-1135 G7"))
            add(CompareAnswer("RAM", "8GB", "8GB"))
            add(CompareAnswer("무게", "약 1,480g", "약 1,480g"))
        }

        val compareResultRVAdapter = CompareResultRVAdapter(compareAnswers)
        binding.compareResultRv.adapter = compareResultRVAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.compareResultRv.layoutManager = layoutManager
    }
}