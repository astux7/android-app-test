package com.basta.demo.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "#${coin.rank}. ${coin.name}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                )

                Text(
                    text = if (coin.isNew) "[New]" else "",
                    color = if (coin.isActive) Color.Green else Color.Red,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                coin.logo?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier.width(50.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = if (coin.isActive) "Status: active" else "Status: inactive",
                    color = if (coin.isActive) Color.Green else Color.Red,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Text(
                    text = "Started at: ${coin.startedAt}",
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Text(
                text = "${coin.description}",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 10
            )

        }
    }
}