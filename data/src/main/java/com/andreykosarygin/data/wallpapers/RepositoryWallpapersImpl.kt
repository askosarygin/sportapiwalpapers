package com.andreykosarygin.data.wallpapers

import android.graphics.Bitmap
import com.andreykosarygin.common.WallpaperDB
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryWallpapersImpl(
    private val loadBitmap: RequestManager
) : RepositoryWallpapers {

    override suspend fun downloadBitmapFromUrl(url: String): Bitmap {
        return withContext(Dispatchers.IO) {
            loadBitmap.asBitmap().load(url).submit().get()
        }
    }

    override suspend fun loadWallpapersFromDB(): List<WallpaperDB> {
        return listOf(
            WallpaperDB(
                0,
                "Nike",
                10,
                "https://w.forfun.com/fetch/49/498e541aa0cf88be17a0f430fcd6825e.jpeg"
            ),
            WallpaperDB(
                1,
                "Boxing gloves",
                10,
                "https://w.forfun.com/fetch/10/10a8682e2cd764ec14fe0aaee71fc7be.jpeg"
            ),
            WallpaperDB(
                3,
                "Football",
                15,
                "https://w.forfun.com/fetch/0e/0e4d3c46263ae40b15b5fd50908274eb.jpeg"
            ),
            WallpaperDB(
                4,
                "Adidas",
                20,
                "https://w.forfun.com/fetch/ce/ce52f72194c8102c98f1fe4c4745d811.jpeg"
            ),
            WallpaperDB(
                5,
                "Fishing",
                10,
                "https://w.forfun.com/fetch/71/71e036b8a9100c8ac916319c363358d3.jpeg"
            ),
            WallpaperDB(
                6,
                "Basketball",
                10,
                "https://w.forfun.com/fetch/4d/4d2a6af8c5cbc469e17a90f6ae493687.jpeg"
            ),
            WallpaperDB(
                7,
                "Golf",
                15,
                "https://w.forfun.com/fetch/77/7754a21c0e129b66164184cddb64d209.jpeg"
            ),
            WallpaperDB(
                8,
                "Windserfing",
                15,
                "https://w.forfun.com/fetch/b9/b97f81d779f7fb3a32cad43cf9bdd382.jpeg"
            ),
            WallpaperDB(
                9,
                "Horse",
                20,
                "https://w.forfun.com/fetch/5a/5aff67512a669239221014d8fc40cf53.jpeg"
            ),
            WallpaperDB(
                10,
                "Paintball",
                10,
                "https://w.forfun.com/fetch/a8/a8b1b6c48909a06c308f28d7daee9d55.jpeg"
            ),
            WallpaperDB(
                11,
                "Tennis",
                15,
                "https://w.forfun.com/fetch/f3/f3673efd5b4c55badfe5d8f2a6ac9b4d.jpeg"
            ),
            WallpaperDB(
                12,
                "Basketball",
                10,
                "https://w.forfun.com/fetch/b7/b76cb60a636ce314e385bd248fa5d101.jpeg"
            )
        )
    }
}