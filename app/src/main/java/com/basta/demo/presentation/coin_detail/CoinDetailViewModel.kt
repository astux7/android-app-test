package com.basta.demo.presentation.coin_detail

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.basta.demo.common.Constants
import com.basta.demo.common.Resource
import com.basta.demo.domain.models.CoinDetail
import com.basta.demo.domain.use_case.get_coin.GetCoinDetailsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// saved state handle only works from koin 3.2.1 otherwise don't pass params
class CoinDetailViewModel(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<Resource<CoinDetail>>(Resource.Loading())
    val state: State<Resource<CoinDetail>> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinDetailsUseCase(coinId).onEach { result ->
            _state.value = result
        }.launchIn(viewModelScope)
    }
}