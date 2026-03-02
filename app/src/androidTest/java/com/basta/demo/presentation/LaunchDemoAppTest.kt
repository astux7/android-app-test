package com.basta.demo.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.basta.demo.navigation.NavigationRoot
import com.basta.demo.utils.TestKoinModule
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.startKoin

class LaunchDemoAppTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private fun setup() {
        stopKoin()
        startKoin {
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
    fun launchingApp_showsListOfCoins() {
        launchApp()
        composeRule.onNodeWithText("Bitcoin", true).assertIsDisplayed()
        composeRule.onNodeWithText("Ontology", true).assertIsDisplayed()
        composeRule.onNodeWithTag("LAZY_COLUMN").onChildren().assertCountEquals(2)
    }

    @Test
    fun launchingApp_onListItemClick_isDetailPage() {
        launchApp()
        composeRule.onNodeWithText("Bitcoin", true).performClick()
        composeRule.onNodeWithText("#1. Bitcoin").assertIsDisplayed()
    }
}
