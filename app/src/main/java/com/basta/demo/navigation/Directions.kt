package com.basta.demo.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavigationCommand {
    val name: String
    val arguments: List<NamedNavArgument>
}

object Directions {
    val coin_list = object : NavigationCommand {
        override val name: String
            get() = "coin_list"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
    val coin_detail = object : NavigationCommand {
        override val name: String
            get() = "coin_detail/{coinId}"
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument("coinId") { type = NavType.StringType }
            )
    }
}
