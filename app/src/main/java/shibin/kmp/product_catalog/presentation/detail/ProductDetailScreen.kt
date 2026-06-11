package shibin.kmp.product_catalog.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import shibin.kmp.product_catalog.domain.model.Product
import shibin.kmp.product_catalog.presentation.composables.AppTopBar
import shibin.kmp.product_catalog.presentation.composables.CategoryChip
import shibin.kmp.product_catalog.presentation.composables.ImageElement
import shibin.kmp.product_catalog.presentation.composables.StarRating
import shibin.kmp.product_catalog.presentation.composables.StockBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(), onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val product = state.product

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Product Details", subtitle = state.email, showBack = true, onBack = onBack
            )
        }) { padding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(state.error!!)
                }
            }

            state.product != null -> {
                DetailContent(
                    product = product!!, modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
fun DetailContent(product: Product, modifier: Modifier) {

    val pagerState = rememberPagerState(pageCount = { product.images.size })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            HorizontalPager(
                state = pagerState, modifier = Modifier.fillMaxSize()
            ) { page ->
                ImageElement(url = product.images[page], modifier = Modifier.fillMaxSize())
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    repeat(product.images.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(
                                    if (pagerState.currentPage == index) 12.dp
                                    else 8.dp
                                )
                                .background(
                                    color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.outline, shape = CircleShape
                                )
                        )
                    }
                }
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-20).dp), shape = RoundedCornerShape(
                topStart = 24.dp, topEnd = 24.dp
            ), color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = product.title, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = product.brand, color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                StarRating(product.rating)
                Text(
                    text = "₹${product.price}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF2E7D32)
                )
                Text(
                    text = "${product.discountPercentage}% OFF", color = Color.Red,
                    fontSize = 12.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CategoryChip(product.category)
                    StockBadge(product.stock)
                }
                Text(
                    text = "Description", style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = product.description, style = MaterialTheme.typography.bodyLarge
                )
            }

        }
    }


}