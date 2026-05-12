package com.hastakala.shop.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hastakala.shop.ui.screens.AddSaleScreen
import com.hastakala.shop.ui.screens.BestSellerScreen
import com.hastakala.shop.ui.screens.HomeDashboardScreen
import com.hastakala.shop.ui.screens.IncomeLogScreen
import com.hastakala.shop.ui.screens.SplashScreen
import com.hastakala.shop.viewmodel.SalesViewModel

@Composable
fun HastaKalaApp(viewModel: SalesViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeDashboardScreen(
                viewModel = viewModel,
                onAddSale = { navController.navigate(Screen.AddSale.route) },
                onIncomeLog = { navController.navigate(Screen.IncomeLog.route) },
                onBestSeller = { navController.navigate(Screen.BestSeller.route) }
            )
        }
        composable(Screen.AddSale.route) {
            AddSaleScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.BestSeller.route) {
            BestSellerScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.IncomeLog.route) {
            IncomeLogScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object AddSale : Screen("add_sale")
    data object BestSeller : Screen("best_seller")
    data object IncomeLog : Screen("income_log")
}
