package com.basta.demo.domain.use_case.get_coins

import com.basta.demo.common.Resource
import com.basta.demo.domain.models.Coin
import com.basta.demo.domain.models.toCoin
import com.basta.demo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinsUseCase(private val repo: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())

            val coins = repo.getCoins()

            emit(Resource.Success(coins.map { it.toCoin() }))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error, try later."))
        } catch (e: IOException) {
            emit(Resource.Error("Looks like you don't have internet connection."))
        }
    }
}