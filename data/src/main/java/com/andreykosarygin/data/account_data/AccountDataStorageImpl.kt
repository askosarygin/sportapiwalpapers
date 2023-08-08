package com.andreykosarygin.data.account_data

import android.content.SharedPreferences

class AccountDataStorageImpl(
    private val sharedPreferences: SharedPreferences
) : AccountDataStorage {
    private val keyPoints = "key_points"

    override fun savePoints(points: Int) : Boolean {
        val savedPoints = getPoints()
        sharedPreferences.edit().putInt(keyPoints, points + savedPoints).apply()
        return true
    }

    override fun getPoints(): Int = sharedPreferences.getInt(keyPoints, 0)

    override fun saveBoughtWallpaperId(id: Long): Boolean {
        sharedPreferences.edit().putLong("$id", id).apply()
        return true
    }

    override fun checkBoughtWallpaper(id: Long): Boolean {
        return sharedPreferences.getLong(id.toString(), -1L) != -1L
    }
}