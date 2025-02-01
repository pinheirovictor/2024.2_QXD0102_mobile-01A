package com.example.tasksmanagerapp

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object DataStoreUtils {
    private val TASKS_KEY = stringPreferencesKey("tasks")
    private val THEME_KEY = booleanPreferencesKey("is_dark_theme")

    suspend fun saveTasks(context: Context, tasks: String) {
        context.dataStore.edit { prefs ->
            prefs[TASKS_KEY] = tasks
        }
    }

    fun readTasks(context: Context): Flow<String> = context.dataStore.data.map { prefs ->
        prefs[TASKS_KEY] ?: ""
    }

    suspend fun saveTheme(context: Context, isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = isDark
        }
    }

    fun readTheme(context: Context): Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: false
    }
}
