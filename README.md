## Demo App

This App is an example of the type of interview test that many tech companies ask candidates to complete.

This is a small **Android application** written in **Kotlin** that fetches data from a service and displays it on the user interface using **Jetpack Compose**.

## Project structure

The project follows the MVVM architectural pattern 
with clean architecture principles and includes an additional layer called 'UseCase'
that contains the business logic.

## Service

This app is fetching data from [Coin Paprika](https://coinpaprika.com) service. 
Which is free API and does not require any authentication. This application has 2 API request:
* to get all items `/v1/coins`
* to get item details `/v1/coins/:id`


## Libraries/ Technologies used:
* Retrofit - for network request
* Koin - Dependency injection (bonus library not necessary for this project to work)
* Jetpack compose tool for UI

## Testing


## Improvements