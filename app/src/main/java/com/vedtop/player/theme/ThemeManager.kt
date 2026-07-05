package com.vedtop.player.theme

import android.content.Context
import androidx.core.content.edit

class ThemeManager(private val context: Context) {

    companion object {
        const val THEME_DEFAULT = "DEFAULT"
        const val THEME_OCEAN = "OCEAN"
        const val THEME_SUNSET = "SUNSET"
        const val THEME_FOREST = "FOREST"
    }

    data class Theme(
        val primary: String,
        val secondary: String,
        val accent: String,
        val background: String,
        val text: String
    )

    private val themes = mapOf(
        THEME_DEFAULT to Theme(
            primary = "#5B4CF6",
            secondary = "#4238C4",
            accent = "#8A7DFF",
            background = "#121212",
            text = "#FFFFFF"
        ),
        THEME_OCEAN to Theme(
            primary = "#00B4D8",
            secondary = "#0077B6",
            accent = "#48CAE4",
            background = "#0A1929",
            text = "#FFFFFF"
        ),
        THEME_SUNSET to Theme(
            primary = "#FF6B35",
            secondary = "#E85D2F",
            accent = "#FF9466",
            background = "#1A1110",
            text = "#FFFFFF"
        ),
        THEME_FOREST to Theme(
            primary = "#2D9D5F",
            secondary = "#1E7A46",
            accent = "#52C97F",
            background = "#0F1A14",
            text = "#FFFFFF"
        )
    )

    fun setTheme(themeName: String) {
        context.getSharedPreferences("app_theme", Context.MODE_PRIVATE).edit {
            putString("current_theme", themeName)
        }
    }

    fun getCurrentTheme(): Theme {
        val prefs = context.getSharedPreferences("app_theme", Context.MODE_PRIVATE)
        val themeName = prefs.getString("current_theme", THEME_DEFAULT) ?: THEME_DEFAULT
        return themes[themeName] ?: themes[THEME_DEFAULT]!!
    }
}
