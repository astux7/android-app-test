package com.basta.demo.presentation.coin_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.basta.demo.presentation.coin_detail.components.CoinDetailBody
import com.basta.demo.presentation.coin_detail.components.CoinDetailHeader
import com.basta.demo.presentation.coin_detail.components.CoinDetailStatus
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = getViewModel(),
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        state.coin?.let { coin ->

            CoinDetailHeader(coin)

            Divider(
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 10.dp)
                    .border(BorderStroke(2.dp, Color.Black)),
            )

            CoinDetailStatus(coin)

            Divider(
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 10.dp)
                    .border(BorderStroke(2.dp, Color.Black)),
            )

            CoinDetailBody(coin)
        }
    }
}