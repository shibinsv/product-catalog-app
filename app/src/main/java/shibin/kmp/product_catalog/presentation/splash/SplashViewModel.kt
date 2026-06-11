package shibin.kmp.product_catalog.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shibin.kmp.product_catalog.datastore.SessionManager
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val loggedIn = sessionManager.isLoggedInFlow.first()
            _uiState.value = SplashUiState(isLoading = false, isLoggedIn = loggedIn)
        }
    }
}