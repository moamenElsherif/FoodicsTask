package com.app.foodicstask.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.foodicstask.R

@Composable
fun ViewOrderBar(
    totalPrice: Double,
    onViewOrderClick: () -> Unit
) {
    Button(
        onClick = onViewOrderClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(stringResource(R.string.view_order_total, totalPrice))
    }
}
