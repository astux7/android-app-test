package com.basta.demo.utils

import com.basta.demo.data.remote.dto.CoinDetailDto
import com.basta.demo.data.remote.dto.CoinDto
import com.basta.demo.domain.repository.CoinRepository

class FakeRepo : CoinRepository {
    private val coinList = mutableListOf<CoinDto>()
    private val coinDetailList = mutableListOf<CoinDetailDto>()

    private val btc = CoinDto(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        rank = 1,
        isNew = false,
        isActive = true,
        type = "coin"
    )

    private val ont = CoinDto(
        id = "ont-ontology",
        name = "Ontology",
        symbol = "ONT",
        rank = 167,
        isNew = false,
        isActive = true,
        type = "coin"
    )

    private val btcDetail = CoinDetailDto(
        id = "btc-bitcoin",
        description = "Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.",
        name = "Bitcoin",
        symbol = "BTC",
        logo = "https://static.coinpaprika.com/coin/btc-bitcoin/logo.png",
        rank = 1,
        isNew = false,
        isActive = true,
        type = "coin",
        openSource = true,
        orgStructure = "",
        startedAt = "2010-07-17T00:00:00Z"
    )

    private val ontDetail = CoinDetailDto(
        id = "ont-ontology",
        description = "Ontology is a new high-performance public blockchain project a distributed trust collaboration platform.h-performance public blockchains that include a series of complete distributed ledgers and smart contract systems.",
        name = "Ontology",
        symbol = "ONT",
        logo = "https://static.coinpaprika.com/coin/ont-ontology/logo.png",
        rank = 167,
        isNew = false,
        isActive = true,
        type = "coin",
        openSource = true,
        orgStructure = "",
        startedAt = "2015-08-17T00:00:00Z"
    )


    init {
        coinList.addAll(listOf(btc, ont))
        coinDetailList.addAll(listOf(btcDetail, ontDetail))
    }

    override suspend fun getCoins(): List<CoinDto> {
        return coinList.toList()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinDetailList.find {
            it.id == coinId
        } ?: btcDetail
    }
}