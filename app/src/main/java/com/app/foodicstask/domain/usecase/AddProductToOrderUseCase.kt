package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.repository.OrderRepository

class AddProductToOrderUseCase(
    private val repository: OrderRepository
) {
    operator fun invoke(product: Product) {
        repository.addProduct(product)
    }
}
