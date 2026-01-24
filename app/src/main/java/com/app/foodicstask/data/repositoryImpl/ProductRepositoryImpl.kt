package com.app.foodicstask.data.repositoryImpl

import com.app.foodicstask.data.Mappers.toDomain
import com.app.foodicstask.data.local.CategoryEntity
import com.app.foodicstask.data.local.ProductEntity
import com.app.foodicstask.data.local.dao.CategoryDao
import com.app.foodicstask.data.local.dao.ProductDao
import com.app.foodicstask.data.remote.api.ProductApiService
import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: ProductApiService,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun getCategories(): List<Category> {
        val remote = api.getCategories()

        val entities = remote.map {
            CategoryEntity(
                id = it.id.toString(),
                name = it.name
            )
        }

        categoryDao.insertCategories(entities)
        return entities.map { it.toDomain() }
    }

    override suspend fun getProducts(): List<Product> {
        val remote = api.getProducts()

        insertCategories(remote.map { it.toDomain() })
        insertProducts(remote.map { it.toDomain() })

        return getProductsFromDb()
    }

    override suspend fun searchProducts(query: String): List<Product> {
        val products = productDao.searchProducts(query)
        return mapProducts(products)
    }
    private suspend fun insertCategories(remote: List<Product>) {
        val categories = remote
            .mapNotNull { it.category }
            .distinctBy { it.id }
            .map {
                CategoryEntity(
                    id = it.id,
                    name = it.name
                )
            }

        categoryDao.insertCategories(categories)
    }

    private suspend fun insertProducts(remote: List<Product>) {
        val entities = remote.mapNotNull { product ->
            product.category?.let { category ->
                ProductEntity(
                    id = product.id,
                    name = product.name,
                    description = product.description,
                    image = product.imageUrl,
                    price = product.price,
                    categoryId = category.id
                )
            }
        }

        productDao.insertProducts(entities)
    }

    private suspend fun getProductsFromDb(): List<Product> {
        return mapProducts(productDao.getAllProducts())
    }

    private suspend fun mapProducts(
        products: List<ProductEntity>
    ): List<Product> {
        val categories = categoryDao
            .getAllCategories()
            .associateBy { it.id }

        return products.mapNotNull { product ->
            val category = categories[product.categoryId]
                ?.toDomain()
                ?: return@mapNotNull null

            product.toDomain(category)
        }
    }
}
