package com.getit.getit.ui.main.home

import com.getit.getit.ui.main.home.server.MainRecommendResult

interface HomeView {
    fun setMainRecommendProducts(code: Int, result: MainRecommendResult)
}