package com.getit.getit.ui.main.mypage.settings

import android.content.Context
import android.content.SharedPreferences
import com.getit.getit.data.User
import java.util.prefs.PreferenceChangeEvent

/*class SharedManager(context: Context) {
    private val prefs:SharedPreferences= PreferenceHelper.defaultPrefs(context)

    fun saveCurrentUser(user: User){
        prefs["name"]=user.nickname
        prefs["id"] =user.email
        prefs["password"]=user.password

    }
    fun getCurrentUser() :User{
        return User().apply {
            email = prefs["name",""]
            password = prefs["passworkd",""]
            nickname= prefs["nickName",""]

        }
    }
}*/