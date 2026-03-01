package com.basta.demo.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable data object CoinList : NavKey
@Serializable data class CoinDetail(val coinId: String) : NavKey