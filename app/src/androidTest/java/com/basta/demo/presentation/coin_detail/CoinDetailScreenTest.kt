package com.basta.demo.presentation.coin_detail

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import com.basta.demo.utils.TestKoinModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.get
import org.koin.core.context.GlobalContext

class CoinDetailScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    private var savedStateHandle = SavedStateHandle()

    @Before
    fun setUp() {
        GlobalContext.stopKoin()

        GlobalContext.startKoin {
            modules(TestKoinModule.allModule())
        }

        composeRule.setContent {
            savedStateHandle["coinId"] = "btc-bitcoin"

            CoinDetailScreen(CoinDetailViewModel(get(), savedStateHandle))
        }
    }

    @After
    fun tearDown() {
        GlobalContext.stopKoin()
    }

    @Test
    fun passingCoinId_showsCoinDetails() {
        // Verify
        composeRule.onNodeWithText("#1. Bitcoin", true).assertIsDisplayed()
    }
}