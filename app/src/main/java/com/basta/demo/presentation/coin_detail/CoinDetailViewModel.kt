package com.basta.demo.presentation.coin_detail

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.basta.demo.common.Resource
import com.basta.demo.domain.models.CoinDetail
import com.basta.demo.domain.use_case.get_coin.GetCoinDetailsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinDetailViewModel(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    coinId: String,
) : ViewModel() {

    private val _state = mutableStateOf<Resource<CoinDetail>>(Resource.Loading)
    val state: State<Resource<CoinDetail>> = _state

    init {
        getCoin(coinId)
    }

    private fun getCoin(coinId: String) {
        getCoinDetailsUseCase(coinId).onEach { result ->
            _state.value = result
        }.launchIn(viewModelScope)
    }
}