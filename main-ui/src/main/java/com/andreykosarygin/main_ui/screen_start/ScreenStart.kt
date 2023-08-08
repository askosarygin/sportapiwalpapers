package com.andreykosarygin.main_ui.screen_start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.andreykosarygin.common.BalanceMessage
import com.andreykosarygin.common.R
import com.andreykosarygin.common.Routes
import com.andreykosarygin.common.YellowButton
import com.andreykosarygin.main_ui.screen_start.ScreenStartViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenDifficultySelection
import com.andreykosarygin.main_ui.screen_start.ScreenStartViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenWallpapersStore

@Composable
fun ScreenStart(
    navController: NavController,
    viewModel: ScreenStartViewModel
) {
    val model by viewModel.model.collectAsState()

    model.navigationEvent?.use { route ->
        when (route) {
            ScreenDifficultySelection -> navController.navigate(Routes.SCREEN_DIFFICULTY_SELECTION)
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
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxSize()
            ) {
                BalanceMessage(
                    balanceText = stringResource(id = R.string.points_balance),
                    balance = model.points.toString()
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .padding(bottom = 50.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sports),
                        fontSize = 38.sp
                    )
                    Image(
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.FillHeight,
                        painter = painterResource(
                            id = com.andreykosarygin.main_ui.R.drawable.sport_api_wallpapers_logo
                        ),
                        contentDescription = stringResource(id = R.string.content_description_quiz_logo)
                    )
                    Text(
                        text = stringResource(id = R.string.quiz),
                        fontSize = 38.sp
                    )
                }

                YellowButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = stringResource(id = R.string.start_game),
                    fontSize = 30.sp,
                    onClick = {
                        viewModel.buttonNewGamePressed()
                    }
                )

                YellowButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.wallpapers),
                    fontSize = 30.sp,
                    onClick = {
                        viewModel.buttonWallpapersStorePressed()
                    }
                )
            }
        }
    }
}