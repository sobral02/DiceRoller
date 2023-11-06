package com.example.diceroller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diceroller.navigation.Screens

@Composable
fun SixButtonsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate(Screens.Roll.route + "/4") },
        ) {
            Text(text = stringResource(R.string.back), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Screens.YourBusinessCard.route) },
        ) {
            Text(text = stringResource(R.string.screen1), fontSize = 18.sp)
        }

        Button(
            onClick = { navController.navigate(Screens.DiceResult.route) },
        ) {
            Text(text = stringResource(R.string.screen2), fontSize = 18.sp)
        }

        Button(
            onClick = { navController.navigate(Screens.DiceResultWithIncrement.route) },
        ) {
            Text(text = stringResource(R.string.screen3), fontSize = 18.sp)
        }

        Button(
            onClick = { navController.navigate(Screens.DieResultWithTextField.route) },
        ) {
            Text(text = stringResource(R.string.screen5), fontSize = 18.sp)
        }

        Button(
            onClick = { navController.navigate(Screens.DieResultWithRollButton.route) },
        ) {
            Text(text = stringResource(R.string.screen6), fontSize = 18.sp)
        }
    }

}

