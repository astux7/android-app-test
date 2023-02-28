package com.basta.demo.domain.use_case.get_coin

import com.basta.demo.common.Resource
import com.basta.demo.domain.models.CoinDetail
import com.basta.demo.domain.models.toCoinDetail
import com.basta.demo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinDetailsUseCase(private val repo: CoinRepository) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())

            val coin = repo.getCoinById(coinId)

            emit(Resource.Success(coin.toCoinDetail()))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error, try later."))
        } catch (e: IOException) {
            emit(Resource.Error("Looks like you don't have internet connection."))
        }
    }
}