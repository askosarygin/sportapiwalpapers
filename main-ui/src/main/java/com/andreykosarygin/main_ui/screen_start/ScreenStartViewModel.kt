package com.andreykosarygin.main_ui.screen_start

import androidx.lifecycle.viewModelScope
import com.andreykosarygin.common.SportsQuizViewModel
import com.andreykosarygin.common.SportsQuizViewModelSingleLifeEvent
import com.andreykosarygin.main_domain.Interactor
import com.andreykosarygin.main_ui.screen_start.ScreenStartViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenDifficultySelection
import com.andreykosarygin.main_ui.screen_start.ScreenStartViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenWallpapersStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreenStartViewModel(
    private val interactor: Interactor
) : SportsQuizViewModel<ScreenStartViewModel.Model>(Model()) {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val points = interactor.getPointsFromAccountDataStorage()

            updatePoints(points)
        }
    }

    fun buttonNewGamePressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                ScreenDifficultySelection
            )
        )
    }

    fun buttonWallpapersStorePressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                ScreenWallpapersStore
            )
        )
    }

    data class Model(
        val points: Int = 0,
        val navigationEvent: NavigationSingleLifeEvent? = null
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : SportsQuizViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(navigateTo) {
            enum class NavigationDestination {
                ScreenDifficultySelection,
                ScreenWallpapersStore
            }
        }
    }

    private fun updateNavigationEvent(navigationEvent: Model.NavigationSingleLifeEvent) {
        update { it.copy(navigationEvent = navigationEvent) }
    }

    private fun updatePoints(points: Int) {
        update { it.copy(points = points) }
    }
}