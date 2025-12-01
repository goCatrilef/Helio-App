package com.example.helioandes.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "helio_session"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
    
    fun saveUserSession(userId: Int, userName: String, userEmail: String) {
        val editor = prefs.edit()
        editor.putInt(KEY_USER_ID, userId)
        editor.putString(KEY_USER_NAME, userName)
        editor.putString(KEY_USER_EMAIL, userEmail)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }
    
    fun getUserId(): Int {
        return prefs.getInt(KEY_USER_ID, -1)
    }
    
    fun getUserName(): String {
        return prefs.getString(KEY_USER_NAME, "") ?: ""
    }
    
    fun getUserEmail(): String {
        return prefs.getString(KEY_USER_EMAIL, "") ?: ""
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}