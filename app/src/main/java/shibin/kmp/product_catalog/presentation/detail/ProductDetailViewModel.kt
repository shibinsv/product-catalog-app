package shibin.kmp.product_catalog.presentation.detail

import androidx.lifecycle.SavedStateHandle
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
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val sessionManager: SessionManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId = savedStateHandle.get<String>("id")?.toIntOrNull() ?: 0

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeEmail()
        loadProduct()
    }

    private fun observeEmail() {

        viewModelScope.launch {
            sessionManager.emailFlow.collect {
                _uiState.value = _uiState.value.copy(email = it)
            }
        }
    }

    private fun loadProduct() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val product = repository.getProductById(productId)
                _uiState.value = _uiState.value.copy(isLoading = false, product = product)

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}