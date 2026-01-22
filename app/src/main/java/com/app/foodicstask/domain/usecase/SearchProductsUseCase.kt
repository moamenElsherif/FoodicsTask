package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.repository.ProductRepository

class SearchProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): List<Product> {
        return repository.searchProducts(query)
    }
}