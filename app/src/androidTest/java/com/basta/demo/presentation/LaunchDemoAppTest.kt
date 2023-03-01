package com.basta.demo.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.koin.core.context.GlobalContext.startKoin
import com.basta.demo.utils.utils.TestKoinModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.stopKoin

class LaunchDemoAppTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        stopKoin()

        startKoin {
            modules(TestKoinModule.allModule())
        }
        composeRule.setContent { MainScreen() }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun launchingApp_showsListOfCoins() {
        // Verify
        composeRule.onNodeWithText("Bitcoin", true).assertIsDisplayed()
        composeRule.onNodeWithText("Ontology", true).assertIsDisplayed()

        composeRule.onNodeWithTag("LAZY_COLUMN").onChildren().assertCountEquals(2)

    }

    @Test
    fun launchingApp_onListItemClick_isDetailPage() {
        composeRule.onNodeWithText("Bitcoin", true).performClick()
        // Verify
        composeRule.onNodeWithText("#1. Bitcoin").assertIsDisplayed()
    }
}