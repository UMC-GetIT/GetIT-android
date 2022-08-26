package com.getit.getit.ui.main.comparison

import com.getit.getit.ui.main.comparison.data.ProductImageName
import com.getit.getit.ui.main.comparison.server.ComparisonLikeResult

interface ComparisonView {
    fun loadLikedProducts(code: Int, result: ComparisonLikeResult)
}