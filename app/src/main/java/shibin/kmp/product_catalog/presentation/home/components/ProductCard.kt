package shibin.kmp.product_catalog.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import shibin.kmp.product_catalog.domain.model.Product
import shibin.kmp.product_catalog.presentation.composables.ImageElement
import shibin.kmp.product_catalog.presentation.composables.StarRating
import shibin.kmp.product_catalog.presentation.composables.StockStatusBadge

@Composable
fun ProductCard(
    product: Product, onClick: () -> Unit
) {

    product.apply {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(onClick = onClick)
        ) {
            Column {
                ImageElement(
                    url = thumbnail,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
//            AsyncImage(
//                model = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
//                contentDescription = null,
//                modifier = Modifier.size(200.dp)
//            )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(text = title, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "₹$price",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "${discountPercentage}% OFF",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    StarRating(rating)
                    StockStatusBadge(stock)
                }
            }
        }
    }


}