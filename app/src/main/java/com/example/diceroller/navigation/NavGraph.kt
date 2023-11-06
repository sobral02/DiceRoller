
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.diceroller.DiceResult
import com.example.diceroller.DiceResultWithIncrement
import com.example.diceroller.DiceWithButtonAndImage
import com.example.diceroller.DieResultWithRollButton
import com.example.diceroller.DieResultWithTextField
import com.example.diceroller.SixButtonsScreen
import com.example.diceroller.navigation.Screens

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Roll.route + "/1",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screens.Roll.route + "/{result}") { navBackStack ->
            val resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceWithButtonAndImage(navController, resultShow)
        }
        composable(route = Screens.DiceResult.route) {
            DiceResult(navController)
        }
        composable(route = Screens.YourBusinessCard.route) {
            YourBusinessCard(navController)
        }
        composable(route = Screens.DiceResultWithIncrement.route + "?result={result}") { navBackStack ->
            DiceResultWithIncrement(navController, Modifier)
        }
        composable(route = Screens.DieResultWithTextField.route + "?result={result}") { navBackStack ->
            DieResultWithTextField(navController)
        }
        composable(route = Screens.DieResultWithRollButton.route) {
            DieResultWithRollButton(navController = navController) { result ->
                navController.navigate("${Screens.Roll.route}/$result")
            }
        }

        composable(route = "six_buttons_screen") {
            SixButtonsScreen(navController)
        }
    }
}

