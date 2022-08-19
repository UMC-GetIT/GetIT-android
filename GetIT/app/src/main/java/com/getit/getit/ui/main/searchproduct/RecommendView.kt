package com.getit.getit.ui.main.searchproduct

interface RecommendView {
    fun onGetRecommendLoading()
    fun onGetRecommendSuccess(code: Int, result: List<RecommendResult>)
    fun onGetRecommendFailure(code: Int, message: String)
}