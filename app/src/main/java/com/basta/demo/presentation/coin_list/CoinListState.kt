package com.basta.demo.presentation.coin_list

import com.basta.demo.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)