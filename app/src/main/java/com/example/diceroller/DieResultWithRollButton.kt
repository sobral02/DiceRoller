package com.example.diceroller

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import com.example.diceroller.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DieResultWithRollButton(
    navController: NavController,
    onBackClick: (Int) -> Unit,

) {
    var result by remember { mutableStateOf(6) }

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
                     result = (1..6).random()
                    canRoll=false
                    delay(1000)
                    canRoll=true
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            //do nothing
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = result.toString(),modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) })

        Button(
            onClick = {
                result = (1..6).random() // Simula a rolagem do dado
            },
        ) {
            Text(text = (stringResource(R.string.roll)), fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onBackClick(result)
                navController.navigate("${Screens.Roll.route}/$result")

            },
        ) {
            Text(text = (stringResource(R.string.back)), fontSize = 20.sp)
        }
    }

    DisposableEffect(Unit) {
        sensorManager.registerListener(sensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }
}
