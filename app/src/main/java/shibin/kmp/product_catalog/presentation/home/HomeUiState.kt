package shibin.kmp.product_catalog.presentation.home

import shibin.kmp.product_catalog.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val selectedCategory: String = "All",
    val email: String = "",
    val error: String? = null
)