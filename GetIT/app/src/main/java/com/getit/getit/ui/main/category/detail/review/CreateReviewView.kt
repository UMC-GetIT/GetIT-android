package com.getit.getit.ui.main.category.detail.review

interface CreateReviewView {
    fun onCreateReviewLoading()
    fun onCreateReviewSuccess(Code: Int, result: String)
    fun onCreateReviewFailure(Code: Int, message: String)
}