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
        val entities = remote.map { CategoryEntity(it.id.toString(), it.name) }
        categoryDao.insertCategories(entities)

        return entities.map { it.toDomain() }
    }

    override suspend fun getProducts(): List<Product> {
        val remote = api.getProducts()

        val categoryEntities = remote.map {
            CategoryEntity(it.category?.id.toString(), it.category?.name.toString())
        }.distinctBy { it.id }

        categoryDao.insertCategories(categoryEntities)

        val productEntities = remote.map {
            ProductEntity(
                id = it.id.toString(),
                name = it.name,
                description = it.description,
                image = it.image,
                price = it.price,
                categoryId = it.category?.id.toString()
            )
        }

        productDao.insertProducts(productEntities)

        return mapProductsFromDb()
    }

    override suspend fun searchProducts(query: String): List<Product> {
        val products = productDao.searchProducts(query)
        val categories = categoryDao.getAllCategories().associateBy { it.id }

        return products.map { product ->
            val category = categories[product.categoryId]!!.toDomain()
            product.toDomain(category)
        }
    }

    private suspend fun mapProductsFromDb(): List<Product> {
        val products = productDao.getAllProducts()
        val categories = categoryDao.getAllCategories().associateBy { it.id }

        return products.map { product ->
            val category = categories[product.categoryId]!!.toDomain()
            product.toDomain(category)
        }
    }
}
