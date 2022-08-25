package com.getit.getit.ui.main.home.recommend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.data.NameImagePriceProduct
import com.getit.getit.databinding.ActivityRecommendQuestionBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.home.data.RecommendAnswerSet
import com.getit.getit.ui.main.home.server.RecommendResult
import com.getit.getit.ui.main.home.server.RecommendProductsService

class RecommendProductsQuestionActivity  : BaseActivity<ActivityRecommendQuestionBinding>(ActivityRecommendQuestionBinding::inflate), RecommendProductsView {

    private var productKind : Int = 0
    private lateinit var recommendQuestionRVAdapter : RecommendQuestionRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recommendService = RecommendProductsService()
        recommendService.setRecommendView(this)

        var answerMap = recommendQuestionRVAdapter.answerMap


        binding.recommendAnswerSummitBtn.setOnClickListener {
            var answerSet = answerMap[0]?.let {
                answerMap[1]?.let { it1 ->
                    answerMap[2]?.let { it2 ->
                        RecommendAnswerSet(convertProductId(productKind),
                            it, it1, it2
                        )
                    }
                }
            }
            answerSet?.let { it1 -> recommendService.loadRecommendProducts(it1) }
        }
    }
    override fun initAfterBinding() {

        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

        productKind = intent.getIntExtra("productKind", 0)
        recommendQuestionsRVApply()

    }

    private fun recommendQuestionsRVApply() {
        var recommendQuestions = getQuestionSet(productKind)

        recommendQuestionRVAdapter = RecommendQuestionRVAdapter(recommendQuestions, this)
        binding.recommendQuestionRv.adapter = recommendQuestionRVAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recommendQuestionRv.layoutManager = linearLayoutManager
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

    override fun setRecommendProductByAnswer(code: Int, result: List<RecommendResult>) {

        var products = ArrayList<NameImagePriceProduct>()

        for(r in result){
            products.add(NameImagePriceProduct(r.productId,r.name,r.price, r.imageUrl))
        }

        startActivity(Intent(this, RecommendResultActivity::class.java).putExtra("products", products))

    }

    private fun convertProductId(id : Int) : String{
        when(id){
            1-> return "노트북"
            2-> return "휴대폰"
            3-> return "태블릿"
            4-> return "스피커"
            5-> return "데스크탑"
        }
        return ""
    }
}

