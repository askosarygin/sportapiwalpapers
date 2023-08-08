package com.apipofpex.sportapiwalpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreykosarygin.common.DifficultLevel
import com.andreykosarygin.common.Routes
import com.andreykosarygin.common.Wallpaper
import com.andreykosarygin.common.ui.theme.SportApiWallpapersTheme
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelection
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel
import com.andreykosarygin.game_ui.screen_game.ScreenGame
import com.andreykosarygin.game_ui.screen_game.ScreenGameViewModel
import com.andreykosarygin.main_ui.screen_start.ScreenStart
import com.andreykosarygin.main_ui.screen_start.ScreenStartViewModel
import com.andreykosarygin.wallpapers_ui.screen_wallpaper.ScreenWallpaper
import com.andreykosarygin.wallpapers_ui.screen_wallpaper.ScreenWallpaperViewModel
import com.andreykosarygin.wallpapers_ui.screen_wallpapers_store.ScreenWallpapersStore
import com.andreykosarygin.wallpapers_ui.screen_wallpapers_store.ScreenWallpapersStoreViewModel

class MainActivity : ComponentActivity() {
    private fun getApplicationInstance() = application as MyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            SportApiWallpapersTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.SCREEN_START
                ) {
                    composable(route = Routes.SCREEN_START) {
                        ScreenStart(
                            navController = navController,
                            viewModel = ScreenStartViewModel(
                                interactor = getApplicationInstance().interactorImplMain
                            )
                        )
                    }

                    composable(route = Routes.SCREEN_DIFFICULTY_SELECTION) {
                        ScreenDifficultySelection(
                            navController = navController,
                            viewModel = ScreenDifficultySelectionViewModel()
                        )
                    }

                    composable(route = Routes.SCREEN_GAME) {
                        val navArguments = navController.previousBackStackEntry?.savedStateHandle?.get<DifficultLevel>(Routes.SCREEN_GAME)

                        ScreenGame(
                            navController = navController,
                            viewModel = ScreenGameViewModel(
                                navArguments = navArguments,
                                interactor = getApplicationInstance().interactorImplGame
                            )
                        )
                    }

                    composable(route = Routes.SCREEN_WALLPAPERS_STORE) {
                        ScreenWallpapersStore(
                            navController = navController,
                            viewModel = ScreenWallpapersStoreViewModel(
                                interactor = getApplicationInstance().interactorImplWallpapers
                            )
                        )
                    }

                    composable(route = Routes.SCREEN_WALLPAPER) {
                        val navArguments = navController.previousBackStackEntry?.savedStateHandle?.get<Wallpaper>(Routes.SCREEN_WALLPAPER)

                        ScreenWallpaper(
                            navController = navController,
                            viewModel = ScreenWallpaperViewModel(
                                navArguments = navArguments,
                                wallpaperManager = getApplicationInstance().wallpaperManager,
                                interactor = getApplicationInstance().interactorImplWallpapers
                            )
                        )
                    }
                }
            }
        }
    }
}