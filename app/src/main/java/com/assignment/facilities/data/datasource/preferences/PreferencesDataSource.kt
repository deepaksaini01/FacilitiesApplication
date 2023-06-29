package com.assignment.facilities.data.datasource.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PreferencesDataSource @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    fun putLong(key: String, value: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    companion object {
        const val LAST_FETCH_TIME_STAMP = "last_fetch_time_stamp"
    }
}
