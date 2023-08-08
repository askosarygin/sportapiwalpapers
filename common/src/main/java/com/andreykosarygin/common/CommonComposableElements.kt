package com.andreykosarygin.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreykosarygin.common.theme.buttonColorYellow
import com.andreykosarygin.common.theme.textButtonColorBlack

@Composable
fun ButtonBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = 25.dp)
            .height(IntrinsicSize.Max)
            .clickable(onClick = onClick)
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 7.dp, bottom = 5.dp, end = 2.dp),
            painter = painterResource(
                id = R.drawable.icon_back
            ),
            contentDescription = stringResource(id = R.string.quiz),
            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = stringResource(id = R.string.back),
            fontSize = 24.sp,
            color = buttonColorYellow
        )
    }
}

@Composable
fun BalanceMessage(
    modifier: Modifier = Modifier,
    balanceText: String,
    balance: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Text(
            text = balanceText,
            fontSize = 24.sp,
            modifier = Modifier.padding(end = 5.dp)
        )
        Text(
            text = balance,
            fontSize = 24.sp
        )
    }
}

@Composable
fun YellowButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(7.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = textButtonColorBlack
        )
    }
}
