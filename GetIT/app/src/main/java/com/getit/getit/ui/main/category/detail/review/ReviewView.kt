package com.getit.getit.ui.main.category.detail.review

interface ReviewView {
    fun onGetReviewLoading()
    fun onGetReviewSuccess(Code: Int, result: List<ReviewResult>)
    fun onGetReviewFailure(Code: Int, message: String)
}