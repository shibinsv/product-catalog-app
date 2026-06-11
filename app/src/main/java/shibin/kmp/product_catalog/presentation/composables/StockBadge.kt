package shibin.kmp.product_catalog.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shibin.kmp.product_catalog.ui.theme.ErrorRed
import shibin.kmp.product_catalog.ui.theme.SuccessGreen
import shibin.kmp.product_catalog.ui.theme.WarningOrange

@Composable
fun StockBadge(stock: Int) {
    val stockColor = when {
        stock > 50 -> SuccessGreen
        stock > 0 -> WarningOrange
        else -> ErrorRed
    }
    Surface(
        color = stockColor.copy(alpha = 0.15f), shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Stock: $stock",
            color = stockColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun StockStatusBadge(stock: Int) {

    val (text, color) = when {
        stock > 50 -> "Available" to SuccessGreen
        stock > 0 -> "Limited" to WarningOrange
        else -> "Unavailable" to ErrorRed
    }

    Surface(
        color = color.copy(alpha = 0.15f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            color = color,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
        )
    }
}