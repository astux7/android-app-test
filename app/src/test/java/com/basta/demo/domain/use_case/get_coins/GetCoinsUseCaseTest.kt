package com.basta.demo.domain.use_case.get_coins

import app.cash.turbine.test
import com.basta.demo.utils.FakeRepo
import com.basta.demo.common.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCoinsUseCaseTest {
    private lateinit var getCoins: GetCoinsUseCase
    private lateinit var fakeRepo: FakeRepo

    @Before
    fun setUp() {
        fakeRepo = FakeRepo()
        getCoins = GetCoinsUseCase(fakeRepo)
    }

    @Test
    fun `First is loading before data is returned`() = runBlocking {
        getCoins.invoke().test {
            val loading = awaitItem() // first value of emit is Loading
            Assert.assertTrue(loading is Resource.Loading)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Get all coins from Paprika API`() = runBlocking {
        getCoins.invoke().test {
            val loading = awaitItem() // first value of emit is Loading
            assertTrue(loading is Resource.Loading)

            val result = awaitItem() // next value

            assertTrue(result is Resource.Success)

            assertTrue((result as Resource.Success).data.isNotEmpty())

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `First rank in result is Bitcoin`() = runBlocking {
        getCoins.invoke().test {
            val name = "Bitcoin"
            val loading = awaitItem() // first value of emit is Loading
            assertTrue(loading is Resource.Loading)

            val result = awaitItem() // next value

            Assert.assertEquals((result as Resource.Success).data.first().name, name)

            cancelAndConsumeRemainingEvents()
        }
    }
}