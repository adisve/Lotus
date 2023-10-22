package android.app.lotus.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

object PrefKey {
    const val isDarkTheme = "isDarkTheme"
}

@Singleton
class PreferenceService @Inject constructor(private val sharedPreferences: SharedPreferences) {

    @Suppress("UNCHECKED_CAST")
    fun <T> getPreference(key: String, default: T): T {
        val prefs = sharedPreferences
        return when (default) {
            is String -> prefs.getString(key, default as String) as T
            is Int -> prefs.getInt(key, default as Int) as T
            is Boolean -> prefs.getBoolean(key, default as Boolean) as T
            is Float -> prefs.getFloat(key, default as Float) as T
            is Long -> prefs.getLong(key, default as Long) as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    fun <T> setPreference(key: String, value: T) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> throw IllegalArgumentException("Unsupported type")
        }
        editor.apply()
    }
}
