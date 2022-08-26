package com.getit.getit.ui.main.comparison

import com.getit.getit.ui.main.comparison.server.ComparisonResultListResult

interface ComparisonAfterView {
    fun loadComparisonResult(code: Int, result: List<ComparisonResultListResult>)
}