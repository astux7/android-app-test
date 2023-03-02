package com.basta.demo.domain.repository

import com.basta.demo.data.remote.dto.CoinDetailDto
import com.basta.demo.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins() : List<CoinDto>
    suspend fun getCoinById(coinId: String) : CoinDetailDto
}