package com.example.personallibraryapp

import android.content.Context
import android.content.SharedPreferences

/**
 * Utility class to manage user settings via SharedPreferences.
 */
class SettingsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("library_settings", Context.MODE_PRIVATE)

    var isDarkModeEnabled: Boolean
        get() = prefs.getBoolean("dark_mode", false)
        set(value) = prefs.edit().putBoolean("dark_mode", value).apply()

    var sortByRating: Boolean
        get() = prefs.getBoolean("sort_by_rating", false)
        set(value) = prefs.edit().putBoolean("sort_by_rating", value).apply()
}