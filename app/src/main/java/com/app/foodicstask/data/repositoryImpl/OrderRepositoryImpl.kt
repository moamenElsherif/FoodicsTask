package com.app.foodicstask.data.repositoryImpl

import com.app.foodicstask.domain.model.OrderItem
import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.repository.OrderRepository

class OrderRepositoryImpl : OrderRepository {

    private val orderItems = mutableMapOf<String, OrderItem>()

    override fun addProduct(product: Product) {
        updateQuantity(product, delta = 1)
    }

    override fun removeProduct(product: Product) {
        updateQuantity(product, delta = -1)
    }

    override fun clearOrder() {
        orderItems.clear()
    }

    override fun getOrderItems(): List<OrderItem> =
        orderItems.values.toList()

    override fun getTotalPrice(): Double =
        orderItems.values.sumOf { it.product.price * it.quantity }

    private fun updateQuantity(product: Product, delta: Int) {
        val existing = orderItems[product.id]

        val newQuantity = (existing?.quantity ?: 0) + delta

        when {
            newQuantity <= 0 -> orderItems.remove(product.id)
            existing == null -> orderItems[product.id] = OrderItem(product, 1)
            else -> orderItems[product.id] =
                existing.copy(quantity = newQuantity)
        }
    }
}
