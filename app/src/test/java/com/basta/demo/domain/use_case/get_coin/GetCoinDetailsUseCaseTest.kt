package com.basta.demo.domain.use_case.get_coin

import app.cash.turbine.test
import com.basta.demo.utils.FakeRepo
import com.basta.demo.common.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetCoinDetailsUseCaseTest {
    private lateinit var getCoinDetails: GetCoinDetailsUseCase
    private lateinit var fakeRepo: FakeRepo

    @Before
    fun setUp() {
        fakeRepo = FakeRepo()
        getCoinDetails = GetCoinDetailsUseCase(fakeRepo)
    }

    @Test
    fun `First is loading before data is returned`() = runBlocking {
        val coinId = "ont-ontology"
        getCoinDetails.invoke(coinId).test {
            val loading = awaitItem() // first value of emit is Loading
            assertTrue(loading is Resource.Loading)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Get Ontology coin details from Paprika API`() = runBlocking {
        val coinId = "ont-ontology"
        getCoinDetails.invoke(coinId).test {
            val loading = awaitItem() // first value of emit is Loading
            assertTrue(loading is Resource.Loading)

            val result = awaitItem() // next value

            assertTrue(result.data?.id == coinId)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Get Bitcoin coin details from Paprika API`() = runBlocking {
        val coinId = "btc-bitcoin"
        val name = "Bitcoin"
        getCoinDetails.invoke(coinId).test {
            val loading = awaitItem() // first value of emit is Loading
            assertTrue(loading is Resource.Loading)

            val result = awaitItem() // next value

            assertTrue(result.data?.id == coinId)
            assertEquals(result.data?.name, name)

            cancelAndConsumeRemainingEvents()
        }
    }
}