package com.basta.demo.presentation.coin_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = getViewModel(),
) {
    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            Text(text = coin?.name ?: "")
            Text(text = coin?.description ?: "")
        }
    }
}