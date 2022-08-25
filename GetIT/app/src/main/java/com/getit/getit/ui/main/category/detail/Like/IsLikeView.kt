package com.getit.getit.ui.main.category.detail.Like

interface IsLikeView {
    fun onIsLikeSuccess(Code: Int, result: IsLike)
    fun onIsLikeFailure(Code: Int, message: String)
}