package com.basta.demo.utils.utils

import androidx.lifecycle.SavedStateHandle
import com.basta.demo.domain.repository.CoinRepository
import com.basta.demo.domain.use_case.get_coin.GetCoinDetailsUseCase
import com.basta.demo.domain.use_case.get_coins.GetCoinsUseCase
import com.basta.demo.presentation.coin_detail.CoinDetailViewModel
import com.basta.demo.presentation.coin_list.CoinListViewModel
import com.basta.demo.utils.FakeRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object TestKoinModule {
    fun allModule(): List<Module> =
        listOf(
            repositoryModule,
            useCaseModule,
            viewModelModule,
        )

    private val useCaseModule: Module
        get() = module {
            factory { GetCoinsUseCase(get()) }
            factory { GetCoinDetailsUseCase(get()) }
        }

    private val repositoryModule: Module
        get() = module {
            single<CoinRepository> { FakeRepo() }
        }

    private val viewModelModule: Module
        get() = module {
            viewModel { CoinListViewModel(get()) }
            viewModel { (savedStateHandle: SavedStateHandle) ->
                CoinDetailViewModel(
                    get(),
                    savedStateHandle
                )
            }
        }
}