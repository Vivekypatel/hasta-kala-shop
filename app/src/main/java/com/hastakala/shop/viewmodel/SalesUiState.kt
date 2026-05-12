package com.hastakala.shop.viewmodel

import com.hastakala.shop.data.local.Sale
import com.hastakala.shop.model.SalesFilter
import com.hastakala.shop.model.SalesSummary

data class SalesUiState(
    val allSales: List<Sale> = emptyList(),
    val filteredSales: List<Sale> = emptyList(),
    val summary: SalesSummary = SalesSummary(),
    val filteredSummary: SalesSummary = SalesSummary(),
    val selectedFilter: SalesFilter = SalesFilter.WEEKLY,
    val aiInsight: String = "Start adding sales to discover artisan product trends."
)
