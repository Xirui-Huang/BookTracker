package com.example.booktracker.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView

object FontUtils {
    private const val PREF_NAME = "UserPrefs"
    private const val FONT_KEY = "fontSize"

    fun applyFontSize(context: Context, textView: TextView) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val fontSize = prefs.getString(FONT_KEY, "medium")
        val size = when (fontSize) {
            "small" -> 14f
            "large" -> 22f
            else -> 18f
        }
        textView.textSize = size
    }
}
