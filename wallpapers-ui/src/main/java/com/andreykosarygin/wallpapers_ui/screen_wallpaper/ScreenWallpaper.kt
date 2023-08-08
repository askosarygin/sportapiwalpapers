package com.andreykosarygin.wallpapers_ui.screen_wallpaper

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.andreykosarygin.common.BalanceMessage
import com.andreykosarygin.common.ButtonBack
import com.andreykosarygin.common.R
import com.andreykosarygin.common.Routes
import com.andreykosarygin.common.YellowButton
import com.andreykosarygin.common.theme.alertDialogBackground
import com.andreykosarygin.wallpapers_ui.screen_wallpaper.ScreenWallpaperViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenWallpapersStore

@Composable
fun ScreenWallpaper(
    navController: NavController,
    viewModel: ScreenWallpaperViewModel
) {
    val model by viewModel.model.collectAsState()

    model.navigationEvent?.use { route ->
        when (route) {
            ScreenWallpapersStore -> navController.navigate(Routes.SCREEN_WALLPAPERS_STORE)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WallpaperInfo(
                    modifier = Modifier.fillMaxWidth(),
                    name = model.name,
                    price = if (model.price != -1) "${model.price} points" else "Bought"
                )

                model.imageResource?.let { imageResource ->
                    Layout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
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
                                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                                bitmap = imageResource.asImageBitmap(),
                                contentDescription = stringResource(
                                    id = R.string.content_description_wallpaper_image
                                ),
                                contentScale = ContentScale.Crop
                            )


                        })
                }
            }

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                val context = LocalContext.current
                if (model.buttonBuyVisible) {
                    YellowButton(
                        modifier = Modifier
                            .padding(bottom = 70.dp)
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.buy),
                        fontSize = 20.sp,
                        onClick = {
                            viewModel.buttonBuyPressed()
                        }
                    )
                } else {
                    YellowButton(
                        modifier = Modifier
                            .padding(bottom = 70.dp)
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.set_wallpaper_to_phone),
                        fontSize = 20.sp,
                        onClick = {
                            viewModel.buttonSetWallpaperToPhonePressed()
                            Toast.makeText(
                                context,
                                context.getText(R.string.wallpaper_installed_on_desktop),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }

        if (model.showDialogBuyWallpaper) {
            AlertDialog(
                containerColor = alertDialogBackground,
                shape = RoundedCornerShape(size = 7.dp),
                onDismissRequest = {},
                title = {
                    Text(text = stringResource(id = R.string.do_you_want_buy_this_wallpaper))
                },
                confirmButton = {
                    YellowButton(
                        text = stringResource(id = R.string.yes),
                        fontSize = 18.sp,
                        onClick = { viewModel.buttonYesWhenBuyWallpaperPressed() }
                    )
                },
                dismissButton = {
                    YellowButton(
                        text = stringResource(id = R.string.no),
                        fontSize = 18.sp,
                        onClick = { viewModel.buttonNoWhenBuyWallpaperPressed() }
                    )
                }
            )
        }

        if (model.showDialogNoPoints) {
            AlertDialog(
                containerColor = alertDialogBackground,
                shape = RoundedCornerShape(size = 7.dp),
                onDismissRequest = {},
                title = {
                    Text(text = stringResource(id = R.string.no_enough_points))
                },
                confirmButton = {
                    YellowButton(
                        text = stringResource(id = R.string.ok),
                        fontSize = 18.sp,
                        onClick = { viewModel.buttonOkInNoEnoughPointsDialogPressed() }
                    )
                }
            )
        }
    }
}

@Composable
fun WallpaperInfo(
    modifier: Modifier = Modifier,
    name: String,
    price: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = name,
            fontSize = 24.sp
        )

        Box(contentAlignment = Alignment.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = price,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 5.dp)

                )
            }
        }

    }
}