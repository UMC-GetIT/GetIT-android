package com.getit.getit.ui.main.search

interface SearchView {
    fun onGetCategoryLoading()
    fun onGetCategorySuccess(Code: Int, result: List<CategoryResult>)
    fun onGetCategoryFailure(Code: Int, message: String)
}