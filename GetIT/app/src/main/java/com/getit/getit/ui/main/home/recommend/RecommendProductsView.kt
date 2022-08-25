package com.getit.getit.ui.main.home.recommend

import com.getit.getit.ui.main.home.server.RecommendResult

interface RecommendProductsView {
    fun setRecommendProductByAnswer(code: Int, result: List<RecommendResult>)
}