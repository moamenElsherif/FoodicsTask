package com.app.foodicstask.ui.screens.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.foodicstask.ui.components.OrderItemRow
import com.app.foodicstask.ui.viewModel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreenHost(
    orderViewModel: OrderViewModel = koinViewModel()
) {
    val orderItems by orderViewModel.orderItems.collectAsState()
    val totalPrice by orderViewModel.totalPrice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Your Order", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        if (orderItems.isEmpty()) {
            Text("No items in your order.")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orderItems) { item ->
                    OrderItemRow(item)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Total: $totalPrice",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { orderViewModel.clearOrder() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear Order")
            }
        }
    }
}