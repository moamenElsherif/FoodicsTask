package com.app.foodicstask.ui.screens.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.app.foodicstask.ui.components.ProductItem
import com.app.foodicstask.ui.components.SearchBar
import com.app.foodicstask.ui.components.ViewOrderBar
import com.app.foodicstask.ui.viewModel.OrderViewModel
import com.app.foodicstask.ui.viewModel.ProductsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MenuScreenHost(
    onViewOrderClick: () -> Unit,
    productsViewModel: ProductsViewModel = koinViewModel(),
    orderViewModel: OrderViewModel = koinViewModel()
) {
    val products by productsViewModel.products.collectAsState()
    val searchQuery by productsViewModel.searchQuery.collectAsState()
    val totalPrice by orderViewModel.totalPrice.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = productsViewModel::onSearchQueryChange
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onClick = { orderViewModel.addProduct(product) }
                )
            }
        }

        ViewOrderBar(
            totalPrice = totalPrice,
            onViewOrderClick = onViewOrderClick
        )
    }
}

@Composable
private fun MenuScreen(
) {
}
