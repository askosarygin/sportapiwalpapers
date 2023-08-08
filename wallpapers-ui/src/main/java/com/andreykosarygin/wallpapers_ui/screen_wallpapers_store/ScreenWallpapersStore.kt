package com.andreykosarygin.wallpapers_ui.screen_wallpapers_store

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.andreykosarygin.common.BalanceMessage
import com.andreykosarygin.common.ButtonBack
import com.andreykosarygin.common.R
import com.andreykosarygin.common.Routes
import com.andreykosarygin.wallpapers_ui.screen_wallpapers_store.ScreenWallpapersStoreViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart
import com.andreykosarygin.wallpapers_ui.screen_wallpapers_store.ScreenWallpapersStoreViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenWallpaper

@Composable
fun ScreenWallpapersStore(
    navController: NavController,
    viewModel: ScreenWallpapersStoreViewModel
) {
    val model by viewModel.model.collectAsState()

    model.navigationEvent?.use { route ->
        when (route) {
            ScreenStart -> navController.navigate(Routes.SCREEN_START)
            ScreenWallpaper -> {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Routes.SCREEN_WALLPAPER,
                    model.selectedWallpaper
                )
                navController.navigate(Routes.SCREEN_WALLPAPER)
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f)
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ButtonBack(onClick = {
                        viewModel.buttonBackPressed()
                    })

                    BalanceMessage(
                        balanceText = stringResource(id = R.string.points),
                        balance = model.points.toString()
                    )
                }
            }

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_to_the_wallpapers_store),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 30.dp)
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (model.wallpapers.isEmpty()) {
                            CircularProgressIndicator()
                        } else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                itemsIndexed(items = model.wallpapers) { index, wallpaper ->
                                    Layout(
                                        modifier = Modifier.fillMaxWidth(),
                                        measurePolicy = { measurables, constraints ->
                                            val placeable = measurables[0].measure(
                                                constraints.copy(
                                                    maxHeight = constraints.maxWidth
                                                )
                                            )

                                            layout(constraints.maxWidth, constraints.maxWidth) {
                                                placeable.placeRelative(x = 0, y = 0)
                                            }
                                        },
                                        content = {
                                            Image(
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .clickable(
                                                        onClick = {
                                                            viewModel.wallpaperSelected(index)
                                                        }
                                                    ),
                                                bitmap = wallpaper.imageResource.asImageBitmap(),
                                                contentDescription = stringResource(
                                                    id = R.string.content_description_wallpaper_image
                                                ),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

