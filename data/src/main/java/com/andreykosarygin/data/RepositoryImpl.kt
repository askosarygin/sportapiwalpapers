package com.andreykosarygin.data

import android.graphics.Bitmap
import com.andreykosarygin.common.Difficult
import com.andreykosarygin.common.QuestionInfoDB
import com.andreykosarygin.common.WallpaperDB
import com.andreykosarygin.data.account_data.AccountDataStorage
import com.andreykosarygin.data.db.QuestionsInfoDBStorage
import com.andreykosarygin.data.wallpapers.RepositoryWallpapers
import com.andreykosarygin.game_domain.Repository

class RepositoryImpl(
    private val questionsInfoDBStorage: QuestionsInfoDBStorage,
    private val accountDataStorage: AccountDataStorage,
    private val repositoryWallpapers: RepositoryWallpapers
) : Repository,
    com.andreykosarygin.main_domain.Repository,
    com.andreykosarygin.wallpapers_domain.Repository {

    override suspend fun loadQuestionsInfoDBWithDifficult(difficult: Difficult): List<QuestionInfoDB> =
        questionsInfoDBStorage.getAllWithDifficult(difficult)

    override suspend fun savePointsToAccountDataStorage(points: Int): Boolean =
        accountDataStorage.savePoints(points)

    override suspend fun downloadBitmapFromUrl(url: String): Bitmap =
        repositoryWallpapers.downloadBitmapFromUrl(url)

    override suspend fun getPointsFromAccountDataStorage(): Int =
        accountDataStorage.getPoints()

    override suspend fun loadWallpapersFromDB(): List<WallpaperDB> =
        repositoryWallpapers.loadWallpapersFromDB()

    override suspend fun saveBoughtWallpaperIdToAccountDataStorage(id: Long): Boolean =
        accountDataStorage.saveBoughtWallpaperId(id)

    override suspend fun checkBoughtWallpaperFromAccountDataStorage(id: Long): Boolean =
        accountDataStorage.checkBoughtWallpaper(id)

}