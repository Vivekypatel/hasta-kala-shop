package com.hastakala.shop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hastakala.shop.ai.generateInsight
import com.hastakala.shop.data.local.Sale
import com.hastakala.shop.data.repository.SalesRepository
import com.hastakala.shop.model.SalesFilter
import com.hastakala.shop.model.SalesSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class SalesViewModel(
    private val repository: SalesRepository
) : ViewModel() {
    private val selectedFilter = MutableStateFlow(SalesFilter.WEEKLY)

    val uiState: StateFlow<SalesUiState> = combine(
        repository.getAllSales(),
        selectedFilter
    ) { sales, filter ->
        val filtered = sales.filterBy(filter)
        SalesUiState(
            allSales = sales,
            filteredSales = filtered,
            summary = sales.toSummary(),
            filteredSummary = filtered.toSummary(),
            selectedFilter = filter,
            aiInsight = generateInsight(sales)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SalesUiState()
    )

    fun setFilter(filter: SalesFilter) {
        selectedFilter.update { filter }
    }

    fun saveSale(productCategory: String, color: String, amount: Double) {
        viewModelScope.launch {
            repository.insertSale(
                Sale(
                    productCategory = productCategory,
                    color = color,
                    amount = amount,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    private fun List<Sale>.filterBy(filter: SalesFilter): List<Sale> {
        val now = System.currentTimeMillis()
        val start = when (filter) {
            SalesFilter.WEEKLY -> startOfWeekMillis()
            SalesFilter.MONTHLY -> startOfMonthMillis()
        }
        return filter { it.timestamp in start..now }
    }

    private fun List<Sale>.toSummary(): SalesSummary {
        val now = System.currentTimeMillis()
        val todayStart = startOfTodayMillis()
        val weekStart = startOfWeekMillis()
        val monthStart = startOfMonthMillis()
        val bestCategory = groupBy { it.productCategory }
            .maxByOrNull { entry -> entry.value.size }
            ?.key
            ?: "No sales yet"
        val bestColor = groupBy { it.color }
            .maxByOrNull { entry -> entry.value.size }
            ?.key
            ?: "No color yet"

        return SalesSummary(
            todayTotal = filter { it.timestamp in todayStart..now }.sumOf { it.amount },
            weekTotal = filter { it.timestamp in weekStart..now }.sumOf { it.amount },
            monthTotal = filter { it.timestamp in monthStart..now }.sumOf { it.amount },
            totalIncome = sumOf { it.amount },
            numberOfSales = size,
            bestSellingProduct = bestCategory,
            bestSellingColor = bestColor
        )
    }

    private fun startOfTodayMillis(): Long {
        return LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }

    private fun startOfWeekMillis(): Long {
        return LocalDate.now()
            .minusDays(6)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }

    private fun startOfMonthMillis(): Long {
        return LocalDate.now()
            .withDayOfMonth(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }
}

class SalesViewModelFactory(
    private val repository: SalesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SalesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SalesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
