package com.hastakala.shop.ui.components

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.hastakala.shop.data.local.Sale

private val ChartColors = listOf(
    Color.rgb(217, 95, 50),
    Color.rgb(243, 163, 58),
    Color.rgb(139, 74, 35),
    Color.rgb(94, 130, 89),
    Color.rgb(73, 115, 133),
    Color.rgb(173, 112, 74)
)

@Composable
fun BestSellerPieChart(
    sales: List<Sale>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            PieChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                description.isEnabled = false
                setUsePercentValues(true)
                setDrawEntryLabels(false)
                setHoleColor(Color.TRANSPARENT)
                setTransparentCircleAlpha(0)
                setCenterTextColor(Color.rgb(94, 55, 32))
                setCenterTextSize(14f)
                legend.orientation = Legend.LegendOrientation.VERTICAL
                legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
                legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                legend.textColor = Color.rgb(94, 55, 32)
            }
        },
        update = { chart ->
            val grouped = sales.groupBy { "${it.color} ${it.productCategory}" }
                .mapValues { entry -> entry.value.size.toFloat() }
                .toList()
                .sortedByDescending { it.second }

            val entries = grouped.map { PieEntry(it.second, it.first) }
            val dataSet = PieDataSet(entries, "Best Sellers").apply {
                colors = ChartColors
                valueTextColor = Color.rgb(94, 55, 32)
                valueTextSize = 12f
                sliceSpace = 2f
            }

            chart.centerText = if (sales.isEmpty()) "No sales" else "Best Sellers"
            chart.data = PieData(dataSet)
            chart.invalidate()
        }
    )
}

@Composable
fun CategoryBarChart(
    sales: List<Sale>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            BarChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                description.isEnabled = false
                axisRight.isEnabled = false
                axisLeft.textColor = Color.rgb(94, 55, 32)
                xAxis.textColor = Color.rgb(94, 55, 32)
                xAxis.granularity = 1f
                xAxis.setDrawGridLines(false)
                legend.isEnabled = false
            }
        },
        update = { chart ->
            val grouped = sales.groupBy { it.productCategory }
                .mapValues { entry -> entry.value.sumOf { it.amount }.toFloat() }
                .toList()
                .sortedByDescending { it.second }

            val labels = grouped.map { it.first }
            val entries = grouped.mapIndexed { index, item -> BarEntry(index.toFloat(), item.second) }

            val dataSet = BarDataSet(entries, "Income").apply {
                colors = ChartColors
                valueTextColor = Color.rgb(94, 55, 32)
                valueTextSize = 11f
            }

            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            chart.data = BarData(dataSet).apply {
                barWidth = 0.55f
            }
            chart.invalidate()
        }
    )
}
