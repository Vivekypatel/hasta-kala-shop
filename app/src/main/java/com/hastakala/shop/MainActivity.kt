package com.hastakala.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.hastakala.shop.data.local.HastaKalaDatabase
import com.hastakala.shop.data.repository.SalesRepository
import com.hastakala.shop.ui.HastaKalaApp
import com.hastakala.shop.ui.theme.HastaKalaShopTheme
import com.hastakala.shop.viewmodel.SalesViewModel
import com.hastakala.shop.viewmodel.SalesViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: SalesViewModel by viewModels {
        val database = HastaKalaDatabase.getDatabase(applicationContext)
        SalesViewModelFactory(SalesRepository(database.saleDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HastaKalaShopTheme {
                HastaKalaApp(viewModel = viewModel)
            }
        }
    }
}
