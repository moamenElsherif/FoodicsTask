package com.app.foodicstask.di

import com.app.foodicstask.ui.navigation.NavigationViewModel
import com.app.foodicstask.ui.viewModel.OrderViewModel
import com.app.foodicstask.ui.viewModel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ProductsViewModel(
            getCategoriesUseCase = get(),
            getProductsUseCase = get(),
            searchProductsUseCase = get()
        )
    }

    viewModel {
        OrderViewModel(
            addProductToOrderUseCase = get(),
            clearOrderUseCase = get(),
            getOrderItemsUseCase = get(),
            getTotalPriceUseCase = get()
        )
    }
    viewModel {
        NavigationViewModel()
    }
}