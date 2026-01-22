package com.app.foodicstask.di

import com.app.foodicstask.data.remote.api.ProductApiService
import com.app.foodicstask.data.remote.api.ProductApiServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }
    }

    single<ProductApiService> {
        ProductApiServiceImpl(get<HttpClient>())
    }
}
