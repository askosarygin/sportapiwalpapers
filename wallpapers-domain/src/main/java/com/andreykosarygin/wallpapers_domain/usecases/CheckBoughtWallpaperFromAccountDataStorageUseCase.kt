package com.andreykosarygin.wallpapers_domain.usecases

import com.andreykosarygin.wallpapers_domain.Repository

class CheckBoughtWallpaperFromAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(id: Long): Boolean =
        repository.checkBoughtWallpaperFromAccountDataStorage(id)
}