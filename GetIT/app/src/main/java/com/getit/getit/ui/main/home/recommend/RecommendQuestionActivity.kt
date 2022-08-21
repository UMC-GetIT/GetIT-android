package com.getit.getit.ui.main.home.recommend

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.ActivityRecommendQuestionBinding
import com.getit.getit.ui.BaseActivity

class RecommendQuestionActivity  : BaseActivity<ActivityRecommendQuestionBinding>(ActivityRecommendQuestionBinding::inflate) {

    private var productKind : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
    override fun initAfterBinding() {

        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

        productKind = intent.getIntExtra("productKind", 0)
        setRecommendQuestion()
    }

    private fun setRecommendQuestion() {


        recommendQuestionsRVApply()

        binding.recommendAnswerSummitBtn.setOnClickListener {
            startActivity(Intent(this, RecommendResultActivity::class.java))
        }

    }

    private fun recommendQuestionsRVApply() {
        var recommendQuestions = getQuestionSet(productKind)

        val recommendQuestionRVAdapter = RecommendQuestionRVAdapter(recommendQuestions, this)
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
}

