package com.basta.demo.presentation.coin_detail.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.basta.demo.domain.models.CoinDetail

@Composable
fun CoinDetailBody(coin: CoinDetail) {
    Text(
        text = coin.description.orEmpty(),
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 10
    )
}