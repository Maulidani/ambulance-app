package skripsi.magfira.ambulanceapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStorePreferences @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Settings")
        val USER_IS_LOGIN_KEY = booleanPreferencesKey("user_is_login")
        val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        val USER_ROLE_KEY = stringPreferencesKey("user_role")
        // Add other keys as needed
    }

    val getIsLogin: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_IS_LOGIN_KEY]
        }

    val getToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }

    val getRole: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ROLE_KEY] ?: ""
        }

    suspend fun saveLogin(isLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_IS_LOGIN_KEY] = isLogin
            if (!isLogin) {
                // If isLogin is false, delete other preferences
                preferences.remove(USER_TOKEN_KEY)
                preferences.remove(USER_ROLE_KEY)
                // Add other keys to remove as needed
            }
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    suspend fun saveRole(role: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE_KEY] = role
        }
    }
}
