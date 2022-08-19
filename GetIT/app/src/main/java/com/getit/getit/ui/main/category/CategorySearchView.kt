package com.getit.getit.ui.main.category

interface CategorySearchView {
    fun onGetCategoryLoading()
    fun onGetCategorySuccess(Code: Int, result: CategoryResult)
    fun onGetCategoryFailure(Code: Int, message: String)
}