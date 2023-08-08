package com.andreykosarygin.wallpapers_domain.usecases

import com.andreykosarygin.wallpapers_domain.Repository

class SaveBoughtWallpaperIdToAccountDataStorageUseCase(
    private val repository: Repository
) {
    suspend fun execute(id: Long): Boolean = repository.saveBoughtWallpaperIdToAccountDataStorage(id)
}