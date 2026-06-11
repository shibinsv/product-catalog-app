package shibin.kmp.product_catalog.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shibin.kmp.product_catalog.data.repository.ProductRepository
import shibin.kmp.product_catalog.datastore.SessionManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository, private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        observeEmail()
        loadProducts()
    }

    fun retry() {
        loadProducts()
    }

    private fun observeEmail() {
        viewModelScope.launch {
            sessionManager.emailFlow.collect { email ->
                _uiState.value = _uiState.value.copy(email = email)
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                val products = repository.getProducts()
                _categories.value = listOf("All") + products.map { it.category }.distinct()
                _uiState.value = _uiState.value.copy(
                    isLoading = false, products = products, filteredProducts = products
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun selectCategory(category: String) {
        val filtered = if (category == "All") {
            _uiState.value.products
        } else {
            _uiState.value.products.filter { it.category == category }
        }
        _uiState.value =
            _uiState.value.copy(selectedCategory = category, filteredProducts = filtered)
    }

    fun logout(onLogout: () -> Unit) {
        viewModelScope.launch {
            sessionManager.logout()
            onLogout()
        }
    }
}