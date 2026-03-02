package com.basta.demo.presentation.coin_detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.basta.demo.utils.TestKoinModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.parameter.parametersOf

class CoinDetailScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val coinID = "btc-bitcoin"

    @Before
    fun setUp() {
        stopKoin()

        startKoin {
            modules(TestKoinModule.allModule())
        }

        composeRule.setContent {
            CoinDetailScreen(
                koinViewModel { parametersOf(coinID) }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun passingCoinId_showsCoinDetails() {
        // Verify
        composeRule.onNodeWithText("#1. Bitcoin", true).assertIsDisplayed()
    }
}