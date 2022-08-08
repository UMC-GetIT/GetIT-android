package com.getit.getit.ui.main.mypage.settings

import android.content.Context
import android.content.SharedPreferences

class ChangeProfiles(context: Context) {

    private val prefsFilename = "prefs"
    private val prefsKeyEdt = "userId"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename, 0)

    var userId: String?
        get() = prefs.getString(prefsKeyEdt, "")
        set(value) = prefs.edit().putString(prefsKeyEdt, value).apply()
}