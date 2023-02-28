package com.basta.demo.presentation.coin_detail

import com.basta.demo.domain.models.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)