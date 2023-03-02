package com.basta.demo.presentation.coin_list

import androidx.lifecycle.ViewModel
import com.basta.demo.domain.use_case.get_coins.GetCoinsUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.basta.demo.common.Resource
import com.basta.demo.domain.models.Coin
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<Resource<List<Coin>>>(Resource.Loading())
    val state: State<Resource<List<Coin>>> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            _state.value = result
        }.launchIn(viewModelScope)
    }
}