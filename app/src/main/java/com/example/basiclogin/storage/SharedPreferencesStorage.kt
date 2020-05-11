package com.example.basiclogin.storage

import android.content.Context

class SharedPreferencesStorage(context: Context) : Storage {
    private val sharedPref =
        context.getSharedPreferences("Registration", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            commit()
        }
    }

    override fun getString(key: String): String {
        return sharedPref.getString(key, "")!!
    }
}