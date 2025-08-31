package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthDataStore(
    private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preferences")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    }

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    suspend fun saveLoggedIn() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
        }
    }

    suspend fun saveLogOut() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = false
        }
    }

    suspend fun isUserLoggedIn(): Boolean {
        return context.dataStore.data.first()[IS_LOGGED_IN] ?: false
    }

}