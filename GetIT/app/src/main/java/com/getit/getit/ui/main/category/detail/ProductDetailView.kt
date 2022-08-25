package com.getit.getit.ui.main.category.detail

interface ProductDetailView {
    fun onGetProductDetailLoading()
    fun onGetProductDetailSuccess(Code: Int, result: ProductDetailResult)
    fun onGetProductDetailFailure(Code: Int, message: String)
}