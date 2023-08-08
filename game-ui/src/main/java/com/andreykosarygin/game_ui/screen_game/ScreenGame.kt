package com.andreykosarygin.game_ui.screen_game

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.andreykosarygin.common.BalanceMessage
import com.andreykosarygin.common.ButtonBack
import com.andreykosarygin.common.R
import com.andreykosarygin.common.Routes
import com.andreykosarygin.common.YellowButton
import com.andreykosarygin.common.theme.alertDialogBackground
import com.andreykosarygin.common.theme.buttonColorGreen
import com.andreykosarygin.common.theme.textColorWhite
import com.andreykosarygin.game_ui.screen_game.ScreenGameViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenDifficultySelection
import com.andreykosarygin.game_ui.screen_game.ScreenGameViewModel.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart

//@Preview
//@Composable
//fun Preview() {
//    SportApiWallpapersTheme {
//        ScreenGame()
//    }
//}

@Composable
fun ScreenGame(
    navController: NavController,
    viewModel: ScreenGameViewModel
) {
    val model by viewModel.model.collectAsState()

    model.navigationEvent?.use { route ->
        when (route) {
            ScreenDifficultySelection -> navController.navigate(Routes.SCREEN_DIFFICULTY_SELECTION)
            ScreenStart -> navController.navigate(Routes.SCREEN_START)
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
                        balance = model.earnedPoints.toString()
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = model.timer,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.padding(bottom = 20.dp, start = 15.dp, end = 15.dp),
                    text = model.question,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                AnswerButton(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = model.answerOne,
                    selected = model.answerOneSelected,
                    onClick = {
                        viewModel.answerOneToggleSelection()
                    }
                )

                AnswerButton(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = model.answerTwo,
                    selected = model.answerTwoSelected,
                    onClick = {
                        viewModel.answerTwoToggleSelection()
                    }
                )

                AnswerButton(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = model.answerThree,
                    selected = model.answerThreeSelected,
                    onClick = {
                        viewModel.answerThreeToggleSelection()
                    }
                )

                AnswerButton(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = model.answerFour,
                    selected = model.answerFourSelected,
                    onClick = {
                        viewModel.answerFourToggleSelection()
                    }
                )
            }

            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.fillMaxSize()
            ) {
                YellowButton(
                    modifier = Modifier.padding(bottom = 50.dp),
                    text = stringResource(id = R.string.next_question),
                    fontSize = 20.sp,
                    onClick = {
                        viewModel.buttonNextQuestionPressed()
                    }
                )
            }
        }

        if (model.showAlertDialog) {
            AlertDialog(
                containerColor = alertDialogBackground,
                shape = RoundedCornerShape(size = 7.dp),
                onDismissRequest = {},
                title = {
                    Text(text = stringResource(id = R.string.time_is_over))
                },
                text = {
                    Text(text = stringResource(id = R.string.game_result_first_part) +
                            " ${model.earnedPoints} " +
                            stringResource(id = R.string.game_result_second_part) +
                            " ${model.earnedPoints} " +
                            stringResource(id = R.string.game_result_third_part)
                    )
                },
                confirmButton = {
                    YellowButton(
                        text = stringResource(id = R.string.play_again),
                        fontSize = 18.sp,
                        onClick = { viewModel.buttonPlayAgainPressed() }
                    )
                },
                dismissButton = {
                    YellowButton(
                        text = stringResource(id = R.string.main_screen),
                        fontSize = 18.sp,
                        onClick = { viewModel.buttonMainScreenPressed() }
                    )
                }
            )
        }
    }
}

@Composable
fun AnswerButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(size = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, color = if (selected) buttonColorGreen else textColorWhite)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = text,
            fontSize = 20.sp
        )
    }
}