package com.app.foodicstask.di

import com.app.foodicstask.domain.usecase.AddProductToOrderUseCase
import com.app.foodicstask.domain.usecase.ClearOrderUseCase
import com.app.foodicstask.domain.usecase.GetCategoriesUseCase
import com.app.foodicstask.domain.usecase.GetOrderItemsUseCase
import com.app.foodicstask.domain.usecase.GetProductsUseCase
import com.app.foodicstask.domain.usecase.GetTotalPriceUseCase
import com.app.foodicstask.domain.usecase.SearchProductsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetCategoriesUseCase(get()) }
    factory { GetProductsUseCase(get()) }
    factory { SearchProductsUseCase(get()) }

    factory { AddProductToOrderUseCase(get()) }
    factory { ClearOrderUseCase(get()) }
    factory { GetOrderItemsUseCase(get()) }
    factory { GetTotalPriceUseCase(get()) }
}
