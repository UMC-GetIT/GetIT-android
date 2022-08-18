package com.getit.getit.ui.main.category

interface CategorySearchView {
    fun onGetCategoryLoading()
    fun onGetCategorySuccess(Code: Int, result: List<CategoryResult>)
    fun onGetCategoryFailure(Code: Int, message: String)
}