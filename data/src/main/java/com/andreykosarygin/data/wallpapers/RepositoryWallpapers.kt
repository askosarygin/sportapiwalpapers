package com.andreykosarygin.data.wallpapers

import android.graphics.Bitmap
import com.andreykosarygin.common.WallpaperDB

interface RepositoryWallpapers {

    suspend fun downloadBitmapFromUrl(url: String): Bitmap

    suspend fun loadWallpapersFromDB(): List<WallpaperDB>
}