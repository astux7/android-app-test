package com.basta.demo.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.basta.demo.navigation.Directions
import com.basta.demo.navigation.buildTheGraph
import org.koin.core.context.GlobalContext.startKoin
import com.basta.demo.utils.TestKoinModule
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
        composeRule.setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Directions.coin_list.name
            ) {
                buildTheGraph(navController)
            }
        }
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