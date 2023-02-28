package com.basta.demo.presentation.coin_list

import androidx.lifecycle.ViewModel
import com.basta.demo.domain.use_case.get_coins.GetCoinsUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.basta.demo.common.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error, try later.")
                }
            }
        }.launchIn(viewModelScope)
    }
}