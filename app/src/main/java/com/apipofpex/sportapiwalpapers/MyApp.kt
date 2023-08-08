package com.apipofpex.sportapiwalpapers

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.andreykosarygin.data.RepositoryImpl
import com.andreykosarygin.data.account_data.AccountDataStorageImpl
import com.andreykosarygin.data.db.QuestionsInfoDBStorageImpl
import com.andreykosarygin.data.db.QuestionsInfoDatabase
import com.andreykosarygin.data.wallpapers.RepositoryWallpapersImpl
import com.andreykosarygin.game_domain.usecases.GetPointsFromAccountDataStorageUseCase
import com.andreykosarygin.game_domain.usecases.LoadEasyQuestionsInfoFromDBUseCase
import com.andreykosarygin.game_domain.usecases.LoadHardQuestionsInfoFromDBUseCase
import com.andreykosarygin.game_domain.usecases.LoadNormalQuestionsInfoFromDBUseCase
import com.andreykosarygin.game_domain.usecases.SavePointsToAccountDataStorageUseCase
import com.andreykosarygin.main_domain.InteractorImpl
import com.andreykosarygin.wallpapers_domain.Interactor
import com.andreykosarygin.wallpapers_domain.usecases.CheckBoughtWallpaperFromAccountDataStorageUseCase
import com.andreykosarygin.wallpapers_domain.usecases.LoadWallpapersFromNetUseCase
import com.andreykosarygin.wallpapers_domain.usecases.SaveBoughtWallpaperIdToAccountDataStorageUseCase
import com.bumptech.glide.Glide

class MyApp : Application() {
    private val sharedPreferencesName = "sport_wallpapers_shared_preferences"
    private val roomDbName = "sport_wallpapers_room_db"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var accountDataStorageImpl: AccountDataStorageImpl
    private lateinit var questionsInfoDBStorageImpl: QuestionsInfoDBStorageImpl
    private lateinit var repositoryWallpapersImpl: RepositoryWallpapersImpl
    private lateinit var repositoryImpl: RepositoryImpl

    lateinit var interactorImplGame: com.andreykosarygin.game_domain.InteractorImpl
    lateinit var interactorImplMain: InteractorImpl
    lateinit var interactorImplWallpapers: Interactor

    lateinit var wallpaperManager: WallpaperManager


    override fun onCreate() {
        super.onCreate()

        wallpaperManager = WallpaperManager.getInstance(this)

        sharedPreferences = this.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        accountDataStorageImpl = AccountDataStorageImpl(sharedPreferences)
        questionsInfoDBStorageImpl = QuestionsInfoDBStorageImpl(
            Room.databaseBuilder(
                this,
                QuestionsInfoDatabase::class.java,
                roomDbName
            ).build().questionsInfoDAO()
        )
        repositoryWallpapersImpl = RepositoryWallpapersImpl(Glide.with(this))
        repositoryImpl = RepositoryImpl(
            questionsInfoDBStorageImpl,
            accountDataStorageImpl,
            repositoryWallpapersImpl
        )

        interactorImplGame = com.andreykosarygin.game_domain.InteractorImpl(
            LoadEasyQuestionsInfoFromDBUseCase(repositoryImpl),
            LoadNormalQuestionsInfoFromDBUseCase(repositoryImpl),
            LoadHardQuestionsInfoFromDBUseCase(repositoryImpl),
            GetPointsFromAccountDataStorageUseCase(repositoryImpl),
            SavePointsToAccountDataStorageUseCase(repositoryImpl)
        )

        interactorImplMain = InteractorImpl(
            com.andreykosarygin.main_domain.usecases.GetPointsFromAccountDataStorageUseCase(
                repositoryImpl
            )
        )

        interactorImplWallpapers = com.andreykosarygin.wallpapers_domain.InteractorImpl(
            com.andreykosarygin.wallpapers_domain.usecases.GetPointsFromAccountDataStorageUseCase(
                repositoryImpl
            ),
            com.andreykosarygin.wallpapers_domain.usecases.SavePointsToAccountDataStorageUseCase(
                repositoryImpl
            ),
            SaveBoughtWallpaperIdToAccountDataStorageUseCase(repositoryImpl),
            CheckBoughtWallpaperFromAccountDataStorageUseCase(repositoryImpl),
            LoadWallpapersFromNetUseCase(repositoryImpl)
        )
    }
}