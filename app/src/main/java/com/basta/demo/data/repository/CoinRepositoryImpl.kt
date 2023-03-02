package com.basta.demo.data.repository

import com.basta.demo.data.remote.CoinPaprikaApi
import com.basta.demo.data.remote.dto.CoinDetailDto
import com.basta.demo.data.remote.dto.CoinDto
import com.basta.demo.domain.repository.CoinRepository

class CoinRepositoryImpl(private val api: CoinPaprikaApi) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}