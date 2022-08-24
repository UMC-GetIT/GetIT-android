package com.getit.getit.ui.main.category.detail

interface LikeView {
    fun onGetLikeSuccess(Code: Int, result: String)
    fun onGetLikeFailure(Code: Int, message: String)
}