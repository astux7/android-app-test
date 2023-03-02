package com.basta.demo.presentation.coin_list

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.basta.demo.utils.TestKoinModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin

class CoinListScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        stopKoin()

        startKoin {
            modules(TestKoinModule.allModule())
        }

        composeRule.setContent {
            val navController = rememberNavController()
            CoinListScreen(navController, getViewModel())
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun coinList_showsListOfTwoCoins() {
        // Verify
        composeRule.onNodeWithText("Bitcoin", true).assertIsDisplayed()
        composeRule.onNodeWithText("Ontology", true).assertIsDisplayed()

        composeRule.onNodeWithTag("LAZY_COLUMN").onChildren().assertCountEquals(2)
    }

    @Test
    fun coinList_ListOfCoins_isCorrect() {
        // Verify
        composeRule.onNodeWithText("Ethereum", true).assertDoesNotExist()
    }
}