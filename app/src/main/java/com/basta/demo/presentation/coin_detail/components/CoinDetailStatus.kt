package com.basta.demo.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.basta.demo.R
import com.basta.demo.domain.models.CoinDetail
import com.basta.demo.extentions.toDateFormat

@Composable
fun CoinDetailStatus(coin: CoinDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val status = stringResource(if (coin.isActive) R.string.active else R.string.inactive)
        Text(
            text = stringResource(
                id = R.string.status,
                status
            ),
            color = if (coin.isActive) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.error,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        coin.startedAt?.let {
            Text(
                text = stringResource(
                    id = R.string.start_at,
                    it.toDateFormat()
                ),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}