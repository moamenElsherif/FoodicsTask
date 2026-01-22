package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.model.OrderItem
import com.app.foodicstask.domain.repository.OrderRepository

class GetOrderItemsUseCase(
    private val repository: OrderRepository
) {
    operator fun invoke(): List<OrderItem> {
        return repository.getOrderItems()
    }
}
