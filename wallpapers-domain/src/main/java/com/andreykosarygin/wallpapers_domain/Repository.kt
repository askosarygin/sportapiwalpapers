package com.andreykosarygin.wallpapers_domain

import android.graphics.Bitmap
import com.andreykosarygin.common.WallpaperDB

interface Repository {

    suspend fun downloadBitmapFromUrl(url: String): Bitmap

    suspend fun getPointsFromAccountDataStorage(): Int

    suspend fun savePointsToAccountDataStorage(points: Int): Boolean

    suspend fun loadWallpapersFromDB(): List<WallpaperDB>

    suspend fun saveBoughtWallpaperIdToAccountDataStorage(id: Long) : Boolean

    suspend fun checkBoughtWallpaperFromAccountDataStorage(id: Long): Boolean
}