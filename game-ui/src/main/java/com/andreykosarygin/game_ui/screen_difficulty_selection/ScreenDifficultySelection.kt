package com.andreykosarygin.game_ui.screen_difficulty_selection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.andreykosarygin.common.ButtonBack
import com.andreykosarygin.common.Difficult
import com.andreykosarygin.common.DifficultLevel
import com.andreykosarygin.common.R
import com.andreykosarygin.common.Routes
import com.andreykosarygin.common.YellowButton
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGameEasy
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGameHard
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGameNormal
import com.andreykosarygin.game_ui.screen_difficulty_selection.ScreenDifficultySelectionViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart

@Composable
fun ScreenDifficultySelection(
    navController: NavController,
    viewModel: ScreenDifficultySelectionViewModel
) {
    val model by viewModel.model.collectAsState()

    model.navigationEvent?.use { route ->
        when (route) {
            ScreenStart -> navController.navigate(Routes.SCREEN_START)

            ScreenGameEasy -> {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Routes.SCREEN_GAME,
                    DifficultLevel(Difficult.Easy.name)
                )
                navController.navigate(Routes.SCREEN_GAME)
            }

            ScreenGameNormal -> {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Routes.SCREEN_GAME,
                    DifficultLevel(Difficult.Normal.name)
                )
                navController.navigate(Routes.SCREEN_GAME)
            }

            ScreenGameHard -> {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Routes.SCREEN_GAME,
                    DifficultLevel(Difficult.Hard.name)
                )
                navController.navigate(Routes.SCREEN_GAME)
            }
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
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxSize()
            ) {
                ButtonBack(onClick = {
                    viewModel.buttonBackPressed()
                })
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 50.dp, start = 15.dp, end = 15.dp),
                    text = stringResource(
                        id = R.string.select_difficulty_of_the_quiz
                    ),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )

                YellowButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = stringResource(id = R.string.easy),
                    fontSize = 30.sp,
                    onClick = {
                        viewModel.buttonEasyPressed()
                    }
                )

                YellowButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = stringResource(id = R.string.normal),
                    fontSize = 30.sp,
                    onClick = {
                        viewModel.buttonNormalPressed()
                    }
                )

                YellowButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.hard),
                    fontSize = 30.sp,
                    onClick = {
                        viewModel.buttonHardPressed()
                    }
                )
            }
        }
    }
}