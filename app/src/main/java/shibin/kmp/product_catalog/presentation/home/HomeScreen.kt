package shibin.kmp.product_catalog.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import shibin.kmp.product_catalog.domain.model.Product
import shibin.kmp.product_catalog.presentation.composables.AppTopBar
import shibin.kmp.product_catalog.presentation.home.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onViewProduct: (Product) -> Unit,
    onLogout: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val categories by viewModel.categories.collectAsState()

    val listState = rememberLazyListState()
    val chipState = rememberLazyListState()

    LaunchedEffect(state.selectedCategory) {
        listState.animateScrollToItem(0)
        val selectedIndex = categories.indexOf(state.selectedCategory)
        if (selectedIndex >= 0) {
            chipState.animateScrollToItem(
                index = selectedIndex, scrollOffset = -200
            )
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Product Catalog", subtitle = state.email, actions = {
                    TextButton(
                        onClick = {
                            viewModel.logout { onLogout() }
                        }) {
                        Text("Logout")
                    }
                })
        }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                when {

                    state.isLoading && state.products.isEmpty() -> {
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
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(state.error!!)

                                TextButton(
                                    onClick = { viewModel.retry() }) {
                                    Text("Retry")
                                }
                            }
                        }
                    }

                    else -> {

                        LazyRow(
                            state = chipState, horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = categories) { category ->
                                FilterChip(
                                    selected = state.selectedCategory == category,
                                    onClick = { viewModel.selectCategory(category) },
                                    label = { Text(category.uppercase()) })

                            }
                        }
                        Text(
                            text = "${state.filteredProducts.size} Products",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        if (state.filteredProducts.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No products found")
                            }
                        } else {
                            LazyColumn(modifier = Modifier.weight(1f), state = listState) {
                                items(items = state.filteredProducts) { product ->
                                    ProductCard(
                                        product = product, onClick = { onViewProduct(product) })
                                }
                            }
                        }
                    }

                }
            }

        }
    }
}