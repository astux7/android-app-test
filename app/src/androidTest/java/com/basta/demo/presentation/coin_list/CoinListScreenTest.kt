package com.basta.demo.presentation.coin_list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.basta.demo.navigation.NavigationRoot
import com.basta.demo.utils.TestKoinModule
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.stopKoin


class CoinListScreenTest {

    companion object {
        @JvmStatic
        @BeforeClass
        fun startKoinOnce() {
            stopKoin()

            org.koin.core.context.startKoin {
                modules(TestKoinModule.allModule())
            }
        }

        @JvmStatic
        @AfterClass
        fun stopKoinOnce() {
            stopKoin()
        }
    }

    @get:Rule
    val composeRule =
        createAndroidComposeRule<ComponentActivity>()

    private fun launchApp() {
        composeRule.setContent {
            NavigationRoot()
        }
    }

    @Test
    fun coinList_showsListOfTwoCoins() {
        launchApp()
        // Verify
        composeRule.onNodeWithText("Bitcoin", true).assertIsDisplayed()
        composeRule.onNodeWithText("Ontology", true).assertIsDisplayed()

        composeRule.onNodeWithTag("LAZY_COLUMN").onChildren().assertCountEquals(2)
    }

    @Test
    fun coinList_ListOfCoins_isCorrect() {
        launchApp()
        // Verify
        composeRule.onNodeWithText("Ethereum", true).assertDoesNotExist()
    }
}