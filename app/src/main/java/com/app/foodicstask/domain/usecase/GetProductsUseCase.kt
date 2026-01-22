package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}