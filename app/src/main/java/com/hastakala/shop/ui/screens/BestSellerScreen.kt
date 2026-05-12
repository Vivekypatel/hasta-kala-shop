package com.hastakala.shop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hastakala.shop.model.SalesFilter
import com.hastakala.shop.ui.components.BestSellerPieChart
import com.hastakala.shop.ui.components.CategoryBarChart
import com.hastakala.shop.ui.components.ScreenHeader
import com.hastakala.shop.ui.components.WarmCard
import com.hastakala.shop.viewmodel.SalesViewModel

@Composable
fun BestSellerScreen(
    viewModel: SalesViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScreenHeader(
            title = "Best Sellers",
            subtitle = "Product and color performance analysis",
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SalesFilter.entries.forEach { filter ->
                    FilterChip(
                        selected = uiState.selectedFilter == filter,
                        onClick = { viewModel.setFilter(filter) },
                        label = { Text(filter.title) }
                    )
                }
            }
            WarmCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Product / Color Share",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(310.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BestSellerPieChart(sales = uiState.filteredSales)
                }
            }
            WarmCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Category Revenue",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CategoryBarChart(sales = uiState.filteredSales)
                }
            }
        }
    }
}
