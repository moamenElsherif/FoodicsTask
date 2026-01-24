package com.app.foodicstask.ui.screens.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.model.Product
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
    val products by productsViewModel.products.collectAsStateWithLifecycle()
    val searchQuery by productsViewModel.searchQuery.collectAsStateWithLifecycle()
    val totalPrice by orderViewModel.totalPrice.collectAsStateWithLifecycle()
    MenuScreen(
        products,
        searchQuery,
        totalPrice,
        onViewOrderClick,
        onSearchQueryChange = productsViewModel::onSearchQueryChange,
        onAddProduct = orderViewModel::addProduct
    )

}

@Composable
private fun MenuScreen(
    products: List<Product>,
    searchQuery: String,
    totalPrice: Double,
    onViewOrderClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onAddProduct: (Product) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onClick = { onAddProduct(product) }
                )
            }
        }

        ViewOrderBar(
            totalPrice = totalPrice,
            onViewOrderClick = onViewOrderClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuScreenPreview() {
    MenuScreen(
        products = List(5) { index ->
            Product(
                id = "id_$index",
                name = "Pizza #$index",
                price = 10.0,
                description = "description",
                category = Category(name = "category", id = "cat_$index"),
                imageUrl = ""
            )
        },
        searchQuery = "",
        totalPrice = 50.0,
        onViewOrderClick = {},
        onSearchQueryChange = { }
    ) { }
}
