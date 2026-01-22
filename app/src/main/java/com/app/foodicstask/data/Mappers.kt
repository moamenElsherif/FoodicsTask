package com.app.foodicstask.data

import com.app.foodicstask.data.local.CategoryEntity
import com.app.foodicstask.data.local.ProductEntity
import com.app.foodicstask.data.remote.dto.CategoryDto
import com.app.foodicstask.data.remote.dto.ProductDto
import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.model.Product

object Mappers {
    fun CategoryDto.toDomain(): Category {
        return Category(
            id = id.toString(),
            name = name
        )
    }
    fun ProductDto.toDomain(): Product {
        val safeCategory = category ?: CategoryDto(
            id = -1,
            name = "Unknown"
        )
        return Product(
            id = id.toString(),
            name = name,
            description = description,
            imageUrl = image,
            price = price,
            category = safeCategory.toDomain()
        )
    }

    fun CategoryEntity.toDomain(): Category {
        return Category(
            id = id,
            name = name
        )
    }

    fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(
            id = id,
            name = name
        )
    }

    fun ProductEntity.toDomain(category: Category): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            imageUrl = image,
            price = price,
            category = category
        )
    }

    fun Product.toEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            description = description,
            image = imageUrl,
            price = price,
            categoryId = category.id
        )
    }
}