package com.andreykosarygin.wallpapers_ui.screen_wallpapers_store

import androidx.lifecycle.viewModelScope
import com.andreykosarygin.common.SportsQuizViewModel
import com.andreykosarygin.common.SportsQuizViewModelSingleLifeEvent
import com.andreykosarygin.common.Wallpaper
import com.andreykosarygin.wallpapers_domain.Interactor
import com.andreykosarygin.wallpapers_ui.screen_wallpapers_store.ScreenWallpapersStoreViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart
import com.andreykosarygin.wallpapers_ui.screen_wallpapers_store.ScreenWallpapersStoreViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenWallpaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreenWallpapersStoreViewModel(
    private val interactor: Interactor
) : SportsQuizViewModel<ScreenWallpapersStoreViewModel.Model>(Model()) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val points = interactor.getPointsFromAccountDataStorage()
            updatePoints(points)

            val wallpapers = interactor.loadWallpapersFromNet()
            updateWallpapers(wallpapers)
        }
    }

    fun buttonBackPressed() {
        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenStart))
    }

    fun wallpaperSelected(position: Int) {
        updateSelectedWallpaper(model.value.wallpapers[position])

        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenWallpaper))
    }

    data class Model(
        val points: Int = 0,
        val wallpapers: List<Wallpaper> = listOf(),
        val selectedWallpaper: Wallpaper? = null,
        val navigationEvent: NavigationSingleLifeEvent? = null
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : SportsQuizViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(
            navigateTo
        ) {
            enum class NavigationDestination {
                ScreenStart,
                ScreenWallpaper
            }
        }
    }

    private fun updateSelectedWallpaper(selectedWallpaper: Wallpaper) {
        update { it.copy(selectedWallpaper = selectedWallpaper) }
    }

    private fun updateWallpapers(wallpapers: List<Wallpaper>) {
        update { it.copy(wallpapers = wallpapers) }
    }

    private fun updateNavigationEvent(navigationEvent: Model.NavigationSingleLifeEvent) {
        update { it.copy(navigationEvent = navigationEvent) }
    }

    private fun updatePoints(points: Int) {
        update { it.copy(points = points) }
    }
}