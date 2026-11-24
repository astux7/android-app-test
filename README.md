# Demo App - Crypto Tracker

A modern **Android Application** written in **Kotlin** that fetches cryptocurrency data from the **CoinPaprika API** and displays it with **Jetpack Compose**.

---

## 🏗️ Architecture & Design Patterns

The project is structured according to **Clean Architecture** principles combined with the **MVVM (Model-View-ViewModel)** pattern:

* **Presentation Layer (`presentation/`)**: Jetpack Compose screens (`CoinListScreen`, `CoinDetailScreen`) and ViewModels managing state.
* **Domain Layer (`domain/`)**: Pure Kotlin business logic containing Domain Models (`Coin`, `CoinDetail`), Repository Interfaces, and Use Cases (`GetCoinsUseCase`, `GetCoinDetailsUseCase`).
* **Data Layer (`data/`)**: Data sources, Retrofit API endpoints (`CoinPaprikaApi`), DTOs, and Repository Implementations (`CoinRepositoryImpl`).

### Why this architecture?
* **Separation of Concerns**: Business logic is independent of UI and data sources.
* **Testability**: Use Cases and ViewModels can easily be unit-tested without Android UI dependencies.
* **Maintainability**: Easy to swap data sources (e.g., adding local database caching) without modifying UI logic.

---

## 🛠️ Technologies & Libraries Used (and Why)

* **[Kotlin](https://kotlinlang.org/)**: Primary language offering safety, conciseness, and coroutine support.
* **[Jetpack Compose & Material 3](https://developer.android.com/jetpack/compose)**: Modern declarative UI toolkit.
  * *Why*: Simplifies UI development with less boilerplate, reactive state binding, and built-in Material 3 design components.
* **[Jetpack Navigation 3](https://developer.android.com/guide/navigation)**: Modern type-safe navigation framework for Jetpack Compose (`rememberNavBackStack`, `NavDisplay`, `entryProvider`).
  * *Why*: Provides compile-time type-safe navigation using `@Serializable` polymorphic `NavKey` objects (`CoinList`, `CoinDetail`), eliminating string-based route errors and managing backstack state cleanly.
* **[Koin](https://insert-koin.io/)**: Pragmatic, Kotlin-native Dependency Injection framework.
  * *Why*: Lightweight setup without annotation processing overhead (KSP/kapt), enabling easy mocking in UI integration tests (`TestKoinModule`).
* **[Kotlin Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html)**: Asynchronous programming and reactive data streams.
  * *Why*: Simplifies non-blocking API calls and emits reactive UI states (`Resource.Loading`, `Resource.Success`, `Resource.Error`).
* **[Retrofit & Gson](https://square.github.io/retrofit/)**: Type-safe HTTP client and JSON converter.
  * *Why*: Industry standard for consuming REST APIs (`CoinPaprika API`) cleanly and efficiently.
* **[Coil](https://coil-kt.github.io/coil/)**: Kotlin First image loading library for Compose.
  * *Why*: Asynchronously fetches and caches remote coin logo images.
* **[Turbine](https://github.com/cashapp/turbine)**: Flow testing library.
  * *Why*: Simplifies testing sequential Flow emissions (`Resource.Loading` -> `Resource.Success`).

---

## 🧪 Testing Strategy

* **Unit Testing (JUnit4 & Turbine)**: Tests Use Cases (`GetCoinsUseCaseTest`, `GetCoinDetailsUseCaseTest`) and utility extensions.
* **UI Integration Testing (Compose Test Rule)**: Tests UI flows in `CoinListScreenTest` using a `FakeRepo` injected via Koin to verify screen behavior without network calls.

---

## 🚀 Future Improvements

* **Offline Caching**: Implement local persistence using **Room** or Retrofit HTTP caching for offline support.
* **Pagination (Paging 3)**: Handle large coin lists incrementally.
* **UI & UX Enhancements**: Add shimmer loading placeholders, pull-to-refresh, and smooth transitions.
* **Migrate to `StateFlow`**: Replace Compose `mutableStateOf` in ViewModels with `StateFlow` + `stateIn` to decouple ViewModels from Compose runtime.
* **Error Retry Mechanism**: Add a "Retry" button on network error screens.
