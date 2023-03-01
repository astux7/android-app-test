package com.basta.demo.di

import androidx.lifecycle.SavedStateHandle
import com.basta.demo.common.Constants
import com.basta.demo.data.remote.CoinPaprikaApi
import com.basta.demo.data.repository.CoinRepositoryImpl
import com.basta.demo.domain.repository.CoinRepository
import com.basta.demo.domain.use_case.get_coin.GetCoinDetailsUseCase
import com.basta.demo.domain.use_case.get_coins.GetCoinsUseCase
import com.basta.demo.presentation.coin_detail.CoinDetailViewModel
import com.basta.demo.presentation.coin_list.CoinListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object KoinModule {
    fun allModule(): List<Module> =
        listOf(
            mainModule,
            apiModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
        )

    private val useCaseModule: Module
        get() = module {
            factory { GetCoinsUseCase(get()) }
            factory { GetCoinDetailsUseCase(get()) }
        }

    private val mainModule: Module
        get() = module {

            factory {
                OkHttpClient.Builder()
                    .callTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build()
            }

            single {
                Retrofit.Builder()
                    .client(get())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

        }

    private val apiModule: Module
        get() = module {
            single { get<Retrofit>().create(CoinPaprikaApi::class.java) }
        }

    private val repositoryModule: Module
        get() = module {
            single<CoinRepository> { CoinRepositoryImpl(get()) }
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