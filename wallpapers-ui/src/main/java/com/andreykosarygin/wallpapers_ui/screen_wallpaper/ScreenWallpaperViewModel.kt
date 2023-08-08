package com.andreykosarygin.wallpapers_ui.screen_wallpaper

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.andreykosarygin.common.SportsQuizViewModel
import com.andreykosarygin.common.SportsQuizViewModelSingleLifeEvent
import com.andreykosarygin.common.Wallpaper
import com.andreykosarygin.wallpapers_domain.Interactor
import com.andreykosarygin.wallpapers_ui.screen_wallpaper.ScreenWallpaperViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenWallpapersStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreenWallpaperViewModel(
    private val navArguments: Wallpaper?,
    private val wallpaperManager: WallpaperManager,
    private val interactor: Interactor
) : SportsQuizViewModel<ScreenWallpaperViewModel.Model>(Model()) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            navArguments?.let { wallpaper ->
                val checkBought =
                    interactor.checkBoughtWallpaperFromAccountDataStorage(wallpaper.id)
                if (!checkBought) {
                    updatePrice(wallpaper.price)
                } else {
                    updateButtonBuyVisible(false)
                }
                val pointsBalance = interactor.getPointsFromAccountDataStorage()
                updateId(wallpaper.id)
                updateName(wallpaper.name)
                updateImageResource(wallpaper.imageResource)
                updatePoints(pointsBalance)
            }
        }
    }

    fun buttonBackPressed() {
        updateNavigationEvent(Model.NavigationSingleLifeEvent(ScreenWallpapersStore))
    }

    fun buttonOkInNoEnoughPointsDialogPressed() {
        updateShowDialogNoPoints(false)
    }

    fun buttonNoWhenBuyWallpaperPressed() {
        updateShowDialogBuyWallpaper(false)
    }

    fun buttonBuyPressed() {
        updateShowDialogBuyWallpaper(true)
    }

    fun buttonYesWhenBuyWallpaperPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            updateShowDialogBuyWallpaper(false)
            val pointsBalance = interactor.getPointsFromAccountDataStorage()
            val price = model.value.price
            if (pointsBalance - price >= 0) {
                interactor.saveBoughtWallpaperIdToAccountDataStorage(model.value.id)
                interactor.savePointsToAccountDataStorage((model.value.price * -1))
                updatePrice(-1)
                updateButtonBuyVisible(false)
                val newPointsBalance = interactor.getPointsFromAccountDataStorage()
                updatePoints(newPointsBalance)
            } else {
                updateShowDialogNoPoints(true)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun buttonSetWallpaperToPhonePressed() {
        model.value.imageResource?.let {
            wallpaperManager.setBitmap(it)
        }
    }

    data class Model(
        val id: Long = 0L,
        val name: String = "",
        val price: Int = -1,
        val points: Int = 0,
        val imageResource: Bitmap? = null,
        val buttonBuyVisible: Boolean = true,
        val navigationEvent: NavigationSingleLifeEvent? = null,
        val showDialogNoPoints: Boolean = false,
        val showDialogBuyWallpaper: Boolean = false
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : SportsQuizViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(
            navigateTo
        ) {
            enum class NavigationDestination {
                ScreenWallpapersStore
            }
        }
    }

    private fun updateShowDialogBuyWallpaper(showDialogBuyWallpaper: Boolean) {
        update { it.copy(showDialogBuyWallpaper = showDialogBuyWallpaper) }
    }

    private fun updateShowDialogNoPoints(showDialogNoPoints: Boolean) {
        update { it.copy(showDialogNoPoints = showDialogNoPoints) }
    }

    private fun updatePoints(points: Int) {
        update { it.copy(points = points) }
    }

    private fun updateId(id: Long) {
        update { it.copy(id = id) }
    }

    private fun updateButtonBuyVisible(buttonBuyVisible: Boolean) {
        update { it.copy(buttonBuyVisible = buttonBuyVisible) }
    }

    private fun updateName(name: String) {
        update { it.copy(name = name) }
    }

    private fun updatePrice(price: Int) {
        update { it.copy(price = price) }
    }

    private fun updateImageResource(imageResource: Bitmap) {
        update { it.copy(imageResource = imageResource) }
    }

    private fun updateNavigationEvent(navigationEvent: Model.NavigationSingleLifeEvent) {
        update { it.copy(navigationEvent = navigationEvent) }
    }
}