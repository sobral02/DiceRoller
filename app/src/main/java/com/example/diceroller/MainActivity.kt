package com.example.diceroller

import NavGraph
import ShakeDetector
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diceroller.navigation.Screens
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}


@Composable
fun DiceWithButtonAndImage(
    navController: NavController,
    resultShow: Int
) {
    var result by remember {
        mutableStateOf(resultShow)
    }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val context = LocalContext.current
    var canRoll by remember { mutableStateOf(true) }

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val coroutineScope = rememberCoroutineScope()

    val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            val x = event?.values?.get(0) ?: 0f
            val y = event?.values?.get(1) ?: 0f
            val z = event?.values?.get(2) ?: 0f

            val acceleration =
                (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)

            if (acceleration >= 1.25 && canRoll) { // Adjust this threshold as needed
                coroutineScope.launch {
                    RollDice(navController)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            //do nothing
        }
    }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .align(Alignment.Center)
                .size(250.dp) // Tamanho do dado
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = resultShow.toString(),
                modifier = Modifier.fillMaxSize()
            )
        }




        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    RollDice(navController) // Chame a função de rolagem do dado quando o botão for clicado
                },
            ) {
                Text(text = stringResource(R.string.roll), fontSize = 24.sp)
            }
        }
    }

    DisposableEffect(Unit) {
        sensorManager.registerListener(sensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }
}

fun RollDice(navController: NavController) {
    Log.d("RollDice", "RollDice function called")
    val result = (1..6).random()
    Log.d("RollDice", "Result: $result")
    when (result) {
        1 -> navController.navigate(Screens.YourBusinessCard.route)
        2 -> navController.navigate(Screens.DiceResult.route)
        3 -> navController.navigate(Screens.DiceResultWithIncrement.route)
        4 -> navController.navigate(Screens.SixButtonsScreen.route)
        5 -> navController.navigate(Screens.DieResultWithTextField.route)
        6 -> navController.navigate(Screens.DieResultWithRollButton.route)
    }
}
