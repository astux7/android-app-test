package com.basta.demo.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.basta.demo.navigation.Directions
import com.basta.demo.navigation.buildTheGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Directions.coin_list.name
    ) {
        buildTheGraph(navController)
    }
}