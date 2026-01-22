package com.app.foodicstask.data.remote.api

import com.app.foodicstask.data.remote.dto.CategoryDto
import com.app.foodicstask.data.remote.dto.ProductDto

interface ProductApiService {

    suspend fun getCategories(): List<CategoryDto>

    suspend fun getProducts(): List<ProductDto>
}
