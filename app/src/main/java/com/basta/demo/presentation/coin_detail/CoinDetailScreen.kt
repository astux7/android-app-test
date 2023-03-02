package com.basta.demo.presentation.coin_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.basta.demo.common.Resource
import com.basta.demo.presentation.coin_detail.components.CoinDetailBody
import com.basta.demo.presentation.coin_detail.components.CoinDetailHeader
import com.basta.demo.presentation.coin_detail.components.CoinDetailStatus
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = getViewModel(),
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        when (state) {
            is Resource.Success -> {

                state.data?.let { coin ->

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

            is Resource.Error -> {
                AnimatedVisibility(visible = !state.message.isNullOrBlank()) {
                    Text(
                        text = state.message.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}