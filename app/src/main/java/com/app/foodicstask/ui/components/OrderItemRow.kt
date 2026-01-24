package com.app.foodicstask.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.foodicstask.R
import com.app.foodicstask.domain.model.OrderItem


@Composable
fun OrderItemRow(item: OrderItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(item.product.name, style = MaterialTheme.typography.titleMedium)
            Text(stringResource(R.string.qty, item.quantity))
        }

        Text(
            "${item.product.price * item.quantity}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
