package com.skyd.raca.model.preference.theme

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.skyd.raca.ext.dataStore
import com.skyd.raca.ext.put
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ThemeNamePreference {
    private const val THEME_NAME = "themeName"
    const val CUSTOM_THEME_NAME = "Custom"

    val values: List<ThemeItem> = mutableListOf(
        ThemeItem(
            name = "Purple",
            keyColor = Color(0xFF62539f)
        ),
        ThemeItem(
            name = "Blue",
            keyColor = Color(0xFF80BBFF)
        ),
        ThemeItem(
            name = "Pink",
            keyColor = Color(0xFFFFD8E4)
        ),
        ThemeItem(
            name = "Yellow",
            keyColor = Color(0xFFE9B666)
        ),
        ThemeItem(
            name = "Green",
            keyColor = Color(0xFF4CAF50)
        ),
    )

    val default = values[0].name

    val key = stringPreferencesKey(THEME_NAME)

    fun put(context: Context, scope: CoroutineScope, value: String) {
        scope.launch(Dispatchers.IO) {
            context.dataStore.put(key, value)
        }
    }

    fun fromPreferences(preferences: Preferences) = preferences[key] ?: default

    data class ThemeItem(val name: String, val keyColor: Color)
}
