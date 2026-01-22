package com.app.foodicstask.ui.viewModel

import androidx.lifecycle.ViewModel
import com.app.foodicstask.domain.model.OrderItem
import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.usecase.AddProductToOrderUseCase
import com.app.foodicstask.domain.usecase.ClearOrderUseCase
import com.app.foodicstask.domain.usecase.GetOrderItemsUseCase
import com.app.foodicstask.domain.usecase.GetTotalPriceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderViewModel(
    private val addProductToOrderUseCase: AddProductToOrderUseCase,
    private val clearOrderUseCase: ClearOrderUseCase,
    private val getOrderItemsUseCase: GetOrderItemsUseCase,
    private val getTotalPriceUseCase: GetTotalPriceUseCase
) : ViewModel() {

    private val _orderItems = MutableStateFlow<List<OrderItem>>(emptyList())
    val orderItems = _orderItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    fun addProduct(product: Product) {
        addProductToOrderUseCase(product)
        refreshOrder()
    }

    fun clearOrder() {
        clearOrderUseCase()
        refreshOrder()
    }

    private fun refreshOrder() {
        _orderItems.value = getOrderItemsUseCase()
        _totalPrice.value = getTotalPriceUseCase()
    }
}
