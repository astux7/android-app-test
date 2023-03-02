package com.basta.demo.presentation.coin_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.basta.demo.common.Constants.LAZY_COLUMN_TAG
import com.basta.demo.common.Resource
import com.basta.demo.navigation.Directions
import com.basta.demo.presentation.coin_list.components.CoinListItem
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = getViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        when (state) {
            is Resource.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize().testTag(LAZY_COLUMN_TAG)) {
                    items(state.data.orEmpty()) { coin ->
                        CoinListItem(
                            onClick = {
                                navController.navigate(Directions.coin_detail.route + "/${coin.id}")
                            },
                            coin = coin
                        )
                    }
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
                            .align(Alignment.Center)
                    )
                }
            }

            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}