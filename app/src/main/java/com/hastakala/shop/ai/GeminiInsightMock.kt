package com.hastakala.shop.ai

import com.hastakala.shop.data.local.Sale

fun generateInsight(salesData: List<Sale>): String {
    if (salesData.isEmpty()) {
        return "Start adding sales to discover artisan product trends."
    }

    val trending = salesData
        .groupBy { "${it.color} ${it.productCategory}s" }
        .maxByOrNull { entry -> entry.value.size }
        ?.key
        ?: "Handmade items"

    val topCategoryRevenue = salesData
        .groupBy { it.productCategory }
        .maxByOrNull { entry -> entry.value.sumOf { it.amount } }
        ?.key
        ?: "products"

    return "$trending are trending, make more. $topCategoryRevenue is your strongest revenue category right now."
}
