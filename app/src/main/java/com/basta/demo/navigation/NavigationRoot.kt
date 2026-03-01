package com.basta.demo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.basta.demo.presentation.coin_detail.CoinDetailScreen
import com.basta.demo.presentation.coin_list.CoinListScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(CoinList::class, CoinList.serializer())
                    subclass(CoinDetail::class, CoinDetail.serializer())
                }
            }
        },
        CoinList
    )

    val entryProvider = entryProvider {
        entry<CoinList> { key -> CoinListScreen(onItemClick = { id -> backStack.add(CoinDetail(coinId = id)) }) }
        entry<CoinDetail> { key -> CoinDetailScreen(coinId = key.coinId) }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider
    )
}