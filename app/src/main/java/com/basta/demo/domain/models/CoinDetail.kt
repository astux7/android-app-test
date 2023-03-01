package com.basta.demo.domain.models

import com.basta.demo.data.remote.dto.CoinDetailDto

data class CoinDetail(
    val description: String?,
    val id: String,
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val logo: String?,
    val name: String?,
    val openSource: Boolean = false,
    val orgStructure: String?,
    val rank: Int?,
    val startedAt: String?,
    val symbol: String?,
    val type: String?
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        description = description,
        id = id,
        isActive = isActive,
        isNew = isNew,
        logo = logo,
        name = name,
        openSource = openSource,
        orgStructure = orgStructure,
        startedAt = startedAt,
        rank = rank,
        symbol = symbol,
        type = type
    )
}