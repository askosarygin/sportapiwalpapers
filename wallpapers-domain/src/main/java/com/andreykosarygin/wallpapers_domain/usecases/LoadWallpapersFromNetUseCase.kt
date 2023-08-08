package com.andreykosarygin.wallpapers_domain.usecases

import com.andreykosarygin.common.Wallpaper
import com.andreykosarygin.wallpapers_domain.Repository

class LoadWallpapersFromNetUseCase(
    private val repository: Repository
) {

    suspend fun execute(): List<Wallpaper> {
        val responseWallpapers = repository.loadWallpapersFromDB()

        return responseWallpapers.map {
            Wallpaper(
                it.id,
                it.name,
                it.price,
                repository.downloadBitmapFromUrl(it.imageUrl)
            )
        }
    }
}