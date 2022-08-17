package com.getit.getit.ui.main.home.recommend

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.ActivityRecommendBinding
import com.getit.getit.databinding.ActivityRecommendQuestionBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.home.data.RecommendQuestion

class RecommendQuestionActivity  : BaseActivity<ActivityRecommendQuestionBinding>(ActivityRecommendQuestionBinding::inflate) {
    override fun initAfterBinding() {
        setRecommendQuestion()
    }

    private fun setRecommendQuestion() {

        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

        recommendQuestionsRVApply()
    }

    private fun recommendQuestionsRVApply() {
        // 더미데이터
        var recommendQuestions = ArrayList<RecommendQuestion>()
        recommendQuestions.apply {
            add(
                RecommendQuestion(
                    "어떤 용도로 사용하시나요?",
                    arrayListOf("문서 작성", "게이밍", "개발", "학습", "디자인", "기타")
                )
            )
            add(
                RecommendQuestion(
                    "예산이 어떻게 되시나요?",
                    arrayListOf("1~10만원", "10~50만원", "50~100만원", "100~200만원", "200만원 이상")
                )
            )
            add(RecommendQuestion("직업이 무엇인가요?", arrayListOf("학생", "직장인", "기타")))
        }

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

