package com.basta.demo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.basta.demo.presentation.coin_detail.CoinDetailScreen
import com.basta.demo.presentation.coin_detail.CoinDetailViewModel
import com.basta.demo.presentation.coin_list.CoinListScreen
import com.basta.demo.presentation.coin_list.CoinListViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
        entry<CoinList> {
            val viewModel = koinViewModel<CoinListViewModel>()
            CoinListScreen(
                onItemClick = { id ->
                    backStack.add(CoinDetail(coinId = id))
                },
                state = viewModel.state
            )
        }

        entry<CoinDetail> { key ->
            val viewModel = koinViewModel<CoinDetailViewModel>(
                key = key.coinId,
                parameters = { parametersOf(key.coinId) }
            )
            CoinDetailScreen(viewModel.state)
        }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider
    )
}