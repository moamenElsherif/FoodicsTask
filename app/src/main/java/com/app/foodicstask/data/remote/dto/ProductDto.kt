package com.app.foodicstask.data.remote.dto

import com.app.foodicstask.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String,
    val price: Double,
    val category: CategoryDto? = null
)
