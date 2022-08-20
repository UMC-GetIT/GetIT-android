package com.getit.getit.ui.main.searchproduct

interface SearchProductView {
    fun onGetSearchProductLoading()
    fun onGetSearchProductSuccess(code: Int, result: List<SearchProductResult>)
    fun onGetSearchProductFailure(code: Int, message: String)
}