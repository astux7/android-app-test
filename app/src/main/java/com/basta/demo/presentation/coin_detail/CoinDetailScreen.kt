package com.basta.demo.presentation.coin_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.basta.demo.common.Resource
import com.basta.demo.domain.models.CoinDetail
import com.basta.demo.presentation.coin_detail.components.CoinDetailBody
import com.basta.demo.presentation.coin_detail.components.CoinDetailHeader
import com.basta.demo.presentation.coin_detail.components.CoinDetailStatus

@Composable
fun CoinDetailScreen(
    state: State<Resource<CoinDetail>>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        when (val result = state.value) {
            is Resource.Success -> {
                CoinDetailHeader(result.data)

                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .border(BorderStroke(2.dp, Color.White)),
                )

                CoinDetailStatus( result.data)

                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .border(BorderStroke(2.dp, Color.White)),
                )

                CoinDetailBody( result.data)
            }

            is Resource.Error -> {
                AnimatedVisibility(visible = !result.message.isNullOrBlank()) {
                    Text(
                        text = result.message.orEmpty(),
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