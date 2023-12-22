package com.capstone.lambiapp.data.database

import android.content.Context
import android.content.SharedPreferences
import com.capstone.lambiapp.ui.HomeFragment

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("session_prefs", Context.MODE_PRIVATE)

    fun saveSessionId(sessionId: String) {
        sharedPreferences.edit().putString("session_id", sessionId).apply()
    }

    fun getSessionId(): String? {
        return sharedPreferences.getString("session_id", null)
    }
    fun saveColor(color:String){
        sharedPreferences.edit().putString("color",color).apply()
    }
    fun getColor(): String? {
        return sharedPreferences.getString("color",null)
    }

    fun saveRecommendationId(recommedationId : String){
        sharedPreferences.edit().putString("recommendation_id", recommedationId).apply()
    }
    fun getRecommendationId(): String? {
        return sharedPreferences.getString("recommendation_id",null)
    }
}