package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.repository.OrderRepository

class ClearOrderUseCase(
    private val repository: OrderRepository
) {
    operator fun invoke() {
        repository.clearOrder()
    }
}
