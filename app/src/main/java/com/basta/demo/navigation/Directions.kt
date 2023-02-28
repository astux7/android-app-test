package com.basta.demo.navigation

import androidx.navigation.NamedNavArgument

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
    val coin = object : NavigationCommand {
        override val name: String
            get() = "coin_details"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
}
