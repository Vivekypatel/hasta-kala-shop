package com.hastakala.shop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.hastakala.shop.ui.components.ScreenHeader
import com.hastakala.shop.ui.components.WarmCard
import com.hastakala.shop.viewmodel.SalesViewModel

private val ProductCategories = listOf("Bag", "Keychain", "Jewelry")
private val Colors = listOf("Red", "Blue", "Green", "Yellow", "Brown", "Cream", "Black", "White")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSaleScreen(
    viewModel: SalesViewModel,
    onBack: () -> Unit
) {
    var category by remember { mutableStateOf(ProductCategories.first()) }
    var color by remember { mutableStateOf(Colors.first()) }
    var amount by remember { mutableStateOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }
    var colorExpanded by remember { mutableStateOf(false) }
    val amountValue = amount.toDoubleOrNull()
    val canSave = amountValue != null && amountValue > 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScreenHeader(
            title = "Quick Bill",
            subtitle = "Add a sale with product, color, and amount",
            onBack = onBack
        )
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            WarmCard(modifier = Modifier.fillMaxWidth()) {
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Product Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        ProductCategories.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    category = item
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }
                ExposedDropdownMenuBox(
                    expanded = colorExpanded,
                    onExpandedChange = { colorExpanded = !colorExpanded }
                ) {
                    OutlinedTextField(
                        value = color,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Color / Design") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = colorExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = colorExpanded,
                        onDismissRequest = { colorExpanded = false }
                    ) {
                        Colors.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    color = item
                                    colorExpanded = false
                                }
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = amount,
                    onValueChange = { input -> amount = input.filter { it.isDigit() || it == '.' } },
                    label = { Text("Sale Amount") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        viewModel.saveSale(category, color, amountValue ?: 0.0)
                        onBack()
                    },
                    enabled = canSave,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Save, contentDescription = null)
                    Text(text = " Save Sale")
                }
            }
        }
    }
}
