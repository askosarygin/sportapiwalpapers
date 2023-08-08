package com.andreykosarygin.wallpapers_domain

import com.andreykosarygin.common.Wallpaper
import com.andreykosarygin.wallpapers_domain.usecases.CheckBoughtWallpaperFromAccountDataStorageUseCase
import com.andreykosarygin.wallpapers_domain.usecases.GetPointsFromAccountDataStorageUseCase
import com.andreykosarygin.wallpapers_domain.usecases.LoadWallpapersFromNetUseCase
import com.andreykosarygin.wallpapers_domain.usecases.SaveBoughtWallpaperIdToAccountDataStorageUseCase
import com.andreykosarygin.wallpapers_domain.usecases.SavePointsToAccountDataStorageUseCase

class InteractorImpl(
    private val getPointsFromAccountDataStorageUseCase: GetPointsFromAccountDataStorageUseCase,
    private val savePointsToAccountDataStorageUseCase: SavePointsToAccountDataStorageUseCase,
    private val saveBoughtWallpaperIdToAccountDataStorageUseCase: SaveBoughtWallpaperIdToAccountDataStorageUseCase,
    private val checkBoughtWallpaperFromAccountDataStorageUseCase: CheckBoughtWallpaperFromAccountDataStorageUseCase,
    private val loadWallpapersFromNetUseCase: LoadWallpapersFromNetUseCase
) : Interactor {

    override suspend fun getPointsFromAccountDataStorage(): Int =
        getPointsFromAccountDataStorageUseCase.execute()

    override suspend fun savePointsToAccountDataStorage(points: Int): Boolean =
        savePointsToAccountDataStorageUseCase.execute(points)

    override suspend fun saveBoughtWallpaperIdToAccountDataStorage(id: Long): Boolean =
        saveBoughtWallpaperIdToAccountDataStorageUseCase.execute(id)

    override suspend fun checkBoughtWallpaperFromAccountDataStorage(id: Long): Boolean =
        checkBoughtWallpaperFromAccountDataStorageUseCase.execute(id)

    override suspend fun loadWallpapersFromNet(): List<Wallpaper> =
        loadWallpapersFromNetUseCase.execute()


}