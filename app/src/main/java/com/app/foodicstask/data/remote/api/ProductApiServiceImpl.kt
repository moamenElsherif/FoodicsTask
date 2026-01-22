package com.app.foodicstask.data.remote.api

import com.app.foodicstask.data.remote.dto.CategoryDto
import com.app.foodicstask.data.remote.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductApiServiceImpl(
    private val client: HttpClient
) : ProductApiService {

    override suspend fun getCategories(): List<CategoryDto> {
        return client.get(CATEGORIES_URL).body()
    }

    override suspend fun getProducts(): List<ProductDto> {
        return client.get(PRODUCTS_URL).body()
    }

    companion object {
        private const val CATEGORIES_URL = "https://my.api.mockaroo.com/categories.json?key=ce047660"
        private const val PRODUCTS_URL = "https://my.api.mockaroo.com/products.json?key=ce047660"
    }
}
