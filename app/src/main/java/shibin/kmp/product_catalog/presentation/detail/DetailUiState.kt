package shibin.kmp.product_catalog.presentation.detail

import shibin.kmp.product_catalog.domain.model.Product

data class DetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val email: String = "",
    val error: String? = null
)