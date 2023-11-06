package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diceroller.navigation.Screens
import kotlin.math.roundToInt

@Composable
fun DiceResultWithIncrement(navController: NavController ,  modifier: Modifier ) {
    var result by remember { mutableStateOf(3) }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Column(
        modifier = modifier.fillMaxSize().wrapContentSize(
            Alignment.Center).pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = result.toString(), modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("${Screens.Roll.route}/$result")},
        ){
            Text(text = (stringResource(R.string.back)), fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { result++ },
        ) {
            Text(text = "Increment by 1", fontSize = 24.sp)
        }
    }

}