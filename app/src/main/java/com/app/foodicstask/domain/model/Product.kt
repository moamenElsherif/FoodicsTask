package com.app.foodicstask.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double,
    val category: Category?,
    val imageUrl: String?
)
