package com.app.foodicstask.domain.repository

import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getCategories(): List<Category>
    suspend fun getProducts(): List<Product>

    suspend fun searchProducts(query: String): List<Product>
}