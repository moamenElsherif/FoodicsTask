package com.app.foodicstask.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.model.Product
import com.app.foodicstask.domain.usecase.GetCategoriesUseCase
import com.app.foodicstask.domain.usecase.GetProductsUseCase
import com.app.foodicstask.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _categories.value = getCategoriesUseCase()
            _products.value = getProductsUseCase()
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query

        viewModelScope.launch {
            _products.value =
                if (query.isBlank()) getProductsUseCase()
                else searchProductsUseCase(query)
        }
    }
}
