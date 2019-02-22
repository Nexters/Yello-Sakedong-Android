package com.yello.nexters.yellosakedong.utils

import android.content.Context
import android.content.SharedPreferences


fun setYellowSakedongKey(context: Context, key: String) {
    val editor: SharedPreferences.Editor = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE).edit()
    editor.putString(YELLOW_SAKE_DONG_KEY, key)
}

fun getYellowSakedongKey(context: Context): String {
    val preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE)
    return preferences.getString(YELLOW_SAKE_DONG_KEY, "")
}