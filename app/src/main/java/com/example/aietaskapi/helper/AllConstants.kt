package com.example.aietaskapi.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import java.text.SimpleDateFormat
import java.util.Calendar

object AllConstants {
    const val TIME_PREF="timePref"
    const val DATABASE_KEY="databaseKey"
    const val TIME_KEY="timeKey"
    const val BASE_URL = "https://hub.dummyapis.com/"

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun View.gone() {
        this.isVisible = false
    }

    fun View.show() {
        this.isVisible = true
    }

    fun lastDate(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(TIME_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getString(TIME_KEY, null)
    }
    @SuppressLint("SimpleDateFormat")
    fun saveCurrentDateTimeToSharedPreferences(context: Context){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // Define your desired date-time format
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(TIME_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TIME_KEY, simpleDateFormat.format(calendar.time))
        editor.apply()
    }

}