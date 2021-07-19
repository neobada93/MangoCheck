package com.todo.check

import android.content.Context
import android.content.SharedPreferences

private const val FILENAME = "prefs"
private const val PREF_REMEMBER = "remember"
private const val PREF_ID = "id"
private const val PREF_GRANT = "grant"
private const val PREF_NAME = "name"

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(FILENAME, 0)
    var remember: String
    get() = prefs.getString(PREF_REMEMBER, "").toString()
    set(value) = prefs.edit().putString(PREF_REMEMBER, value).apply()
    var id: String
    get() = prefs.getString(PREF_ID, "").toString()
    set(value) = prefs.edit().putString(PREF_ID, value).apply()
    var grant: String
    get() = prefs.getString(PREF_GRANT, "").toString()
    set(value) = prefs.edit().putString(PREF_GRANT, value).apply()
    var name: String
    get() = prefs.getString(PREF_NAME, "").toString()
    set(value) = prefs.edit().putString(PREF_NAME, value).apply()
}