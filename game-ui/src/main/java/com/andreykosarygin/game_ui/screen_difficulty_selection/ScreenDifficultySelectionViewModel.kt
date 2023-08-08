package com.andreykosarygin.game_ui.screen_difficulty_selection

import com.andreykosarygin.common.SportsQuizViewModel
import com.andreykosarygin.common.SportsQuizViewModelSingleLifeEvent
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGameEasy
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGameHard
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGameNormal
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart

class ScreenDifficultySelectionViewModel :
    SportsQuizViewModel<ScreenDifficultySelectionViewModel.Model>(Model()) {

    fun buttonBackPressed() {
        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenStart))
    }

    fun buttonEasyPressed() {
        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenGameEasy))
    }

    fun buttonNormalPressed() {
        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenGameNormal))
    }

    fun buttonHardPressed() {
        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenGameHard))
    }

    data class Model(
        val navigationEvent: NavigationSingleLifeEvent? = null
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : SportsQuizViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(
            navigateTo
        ) {
            enum class NavigationDestination {
                ScreenStart,
                ScreenGameEasy,
                ScreenGameNormal,
                ScreenGameHard
            }
        }
    }

    private fun updateNavigationEvent(navigationEvent: Model.NavigationSingleLifeEvent) {
        update { it.copy(navigationEvent = navigationEvent) }
    }
}