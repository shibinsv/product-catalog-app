package shibin.kmp.product_catalog.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shibin.kmp.product_catalog.datastore.SessionManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email, emailError = null)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password, passwordError = null)
    }

    private fun validate(): Boolean {

        val state = _uiState.value

        var emailError: String? = null
        var passwordError: String? = null

        if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            emailError = "Invalid Email"
        }

        if (state.password.isBlank()) {
            passwordError = "Password is required"

        } else if (state.password.length < 8) {
            passwordError = "Minimum 8 characters required"
        }

        _uiState.value = state.copy(emailError = emailError, passwordError = passwordError)

        return emailError == null && passwordError == null
    }

    fun login(onSuccess: () -> Unit) {
        if (!validate()) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            sessionManager.saveLogin(_uiState.value.email)
            _uiState.value = _uiState.value.copy(isLoading = false)
            onSuccess()
        }
    }

}