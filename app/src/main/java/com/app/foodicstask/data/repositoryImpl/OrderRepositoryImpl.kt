package com.app.foodicstask.data.repositoryImpl

import com.app.foodicstask.domain.model.OrderItem
import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.repository.OrderRepository

class OrderRepositoryImpl : OrderRepository {

    private val orderItems = mutableMapOf<String, OrderItem>()

    override fun addProduct(product: Product) {
        val existing = orderItems[product.id]
        if (existing == null) {
            orderItems[product.id] = OrderItem(product, 1)
        } else {
            orderItems[product.id] =
                existing.copy(quantity = existing.quantity + 1)
        }
    }

    override fun removeProduct(product: Product) {
        val existing = orderItems[product.id] ?: return

        if (existing.quantity <= 1) {
            orderItems.remove(product.id)
        } else {
            orderItems[product.id] =
                existing.copy(quantity = existing.quantity - 1)
        }
    }

    override fun clearOrder() {
        orderItems.clear()
    }

    override fun getOrderItems(): List<OrderItem> {
        return orderItems.values.toList()
    }

    override fun getTotalPrice(): Double {
        return orderItems.values.sumOf {
            it.product.price * it.quantity
        }
    }
}
