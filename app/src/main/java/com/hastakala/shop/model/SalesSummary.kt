package com.hastakala.shop.model

data class SalesSummary(
    val todayTotal: Double = 0.0,
    val weekTotal: Double = 0.0,
    val monthTotal: Double = 0.0,
    val totalIncome: Double = 0.0,
    val numberOfSales: Int = 0,
    val bestSellingProduct: String = "No sales yet",
    val bestSellingColor: String = "No color yet"
)
