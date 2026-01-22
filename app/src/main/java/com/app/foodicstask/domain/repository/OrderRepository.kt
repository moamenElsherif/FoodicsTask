package com.app.foodicstask.domain.repository

import com.app.foodicstask.domain.model.OrderItem
import com.app.foodicstask.domain.model.Product

interface OrderRepository {
    fun addProduct(product: Product)

    fun removeProduct(product: Product)

    fun clearOrder()

    fun getOrderItems(): List<OrderItem>

    fun getTotalPrice(): Double
}