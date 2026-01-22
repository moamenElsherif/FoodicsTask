package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.repository.ProductRepository

class GetCategoriesUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}