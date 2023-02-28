package com.basta.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.basta.demo.presentation.coin_detail.CoinDetailScreen
import com.basta.demo.presentation.coin_list.CoinListScreen

fun NavGraphBuilder.buildTheGraph(navController: NavController) {
    composable(Directions.coin_list.name) {
        CoinListScreen(navController)
    }

    composable(Directions.coin_detail.name) {
        CoinDetailScreen(navController)
    }
}