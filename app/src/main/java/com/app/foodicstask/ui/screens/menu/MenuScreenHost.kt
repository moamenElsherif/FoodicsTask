package com.app.foodicstask.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.foodicstask.domain.model.Category
import com.app.foodicstask.domain.model.OrderItem
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
    val isLoading by productsViewModel.isLoading.collectAsStateWithLifecycle()
    val orderItems by orderViewModel.orderItems.collectAsStateWithLifecycle()


    MenuScreen(
        products,
        searchQuery,
        totalPrice,
        orderItems,
        isLoading = isLoading,
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
    orderItems: List<OrderItem>,
    isLoading: Boolean,
    onViewOrderClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onAddProduct: (Product) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            if (isLoading){
                items(5){
                    ProductSkeletonItem()
                }
            }
            else {
                items(products) { product ->
                    val quantity = orderItems.find { it.product.id == product.id }?.quantity ?: 0
                    ProductItem(
                        product = product,
                        quantity = quantity,
                        onClick = { onAddProduct(product) }
                    )
                }
            }
        }

        ViewOrderBar(
            totalPrice = totalPrice,
            onViewOrderClick = onViewOrderClick
        )
    }
}

@Composable
fun ProductSkeletonItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(16.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(4.dp)
                    )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(14.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(4.dp)
                    )
            )
        }
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
        orderItems = listOf(OrderItem(product = Product(
            id = "id",
            name = "Pizza ",
            price = 10.0,
            description = "description",
            category = Category(name = "category", id = "cat_"),
            imageUrl = ""
        ) , quantity = 1)),
        isLoading = false,
        onViewOrderClick = {},
        onSearchQueryChange = { },
    ) { }
}
