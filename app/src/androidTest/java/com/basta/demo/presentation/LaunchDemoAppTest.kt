package com.basta.demo.presentation

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
import org.koin.core.context.startKoin

class LaunchDemoAppTest {

    companion object {
        @JvmStatic
        @BeforeClass
        fun startKoinOnce() {
            stopKoin()

            startKoin {
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
