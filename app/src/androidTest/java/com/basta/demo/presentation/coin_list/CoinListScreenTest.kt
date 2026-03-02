package com.basta.demo.presentation.coin_list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.basta.demo.navigation.NavigationRoot
import com.basta.demo.utils.TestKoinModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin


class CoinListScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private fun setup() {
        stopKoin()
        org.koin.core.context.startKoin {
            androidContext(composeRule.activity)
            modules(TestKoinModule.allModule())
        }
    }

    private fun launchApp() { // Reset Compose UI for each test
        setup()
        composeRule.setContent { NavigationRoot() }
    }

    @After
    fun tearDown() {
        stopKoin()
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