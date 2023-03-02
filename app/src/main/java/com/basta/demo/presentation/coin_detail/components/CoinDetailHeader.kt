package com.basta.demo.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.basta.demo.R
import com.basta.demo.domain.models.CoinDetail

@Composable
fun CoinDetailHeader(coin: CoinDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(
                id = R.string.rank_title,
                coin.rank.toString(), coin.name.orEmpty()
            ),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
        )

        Text(
            text = stringResource(
                id = if (coin.isNew) R.string.new_text else R.string.not_new_text
            ),
            color = if (coin.isActive) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.background,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        coin.logo?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .clip(CircleShape)
            )
        }
    }
}