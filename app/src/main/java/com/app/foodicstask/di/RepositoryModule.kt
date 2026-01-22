package com.app.foodicstask.di

import com.app.foodicstask.data.repositoryImpl.OrderRepositoryImpl
import com.app.foodicstask.data.repositoryImpl.ProductRepositoryImpl
import com.app.foodicstask.domain.repository.OrderRepository
import com.app.foodicstask.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<ProductRepository> {
        ProductRepositoryImpl(
            api = get(),
            categoryDao = get(),
            productDao = get()
        )
    }

    single<OrderRepository> {
        OrderRepositoryImpl()
    }
}
