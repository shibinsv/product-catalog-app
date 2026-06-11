package shibin.kmp.product_catalog.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val EMAIL = stringPreferencesKey("email")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveLogin(email: String) {
        context.dataStore.edit {
            it[EMAIL] = email
            it[IS_LOGGED_IN] = true
        }
    }

    val emailFlow = context.dataStore.data.map { it[EMAIL] ?: "" }

    val isLoggedInFlow = context.dataStore.data.map { it[IS_LOGGED_IN] ?: false }

    suspend fun logout() {
        context.dataStore.edit { it.clear() }
    }

}