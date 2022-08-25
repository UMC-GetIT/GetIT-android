package com.getit.getit.ui.main.category.detail.review

interface ReviewListView {
    fun onGetReviewLoading()
    fun onGetReviewSuccess(Code: Int, result: List<ReviewListResult>)
    fun onGetReviewFailure(Code: Int, message: String)
}