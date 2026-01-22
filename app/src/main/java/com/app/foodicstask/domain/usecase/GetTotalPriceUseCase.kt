package com.app.foodicstask.domain.usecase

import com.app.foodicstask.domain.repository.OrderRepository

class GetTotalPriceUseCase(
    private val repository: OrderRepository
) {
    operator fun invoke(): Double {
        return repository.getTotalPrice()
    }
}
