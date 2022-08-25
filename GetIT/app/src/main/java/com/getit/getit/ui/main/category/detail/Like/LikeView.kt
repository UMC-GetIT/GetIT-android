package com.getit.getit.ui.main.category.detail.Like

interface LikeView {
    fun onGetLikeSuccess(Code: Int, result: String)
    fun onGetLikeFailure(Code: Int, message: String)
}