package com.basta.demo.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavigationCommand {
    val name: String
    val route: String
    val arguments: List<NamedNavArgument>
}

object Directions {
    val coin_list = object : NavigationCommand {
        override val route: String
            get() = "coin_list"
        override val name: String
            get() = route
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    val coin_detail = object : NavigationCommand {
        override val route: String
            get() = "coin_detail"
        override val name: String
            get() = "${route}/{coinId}"
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument("coinId") { type = NavType.StringType }
            )
    }
}
