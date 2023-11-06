package com.example.diceroller.navigation
sealed class Screens(val route: String) {
    object Roll : Screens("roll_screen/{result}")
    object DiceResult : Screens("result_screen")
    object YourBusinessCard: Screens("your_business_card")
    object DiceResultWithIncrement: Screens("result_screen_with_increment")
    object DieResultWithTextField: Screens("result_screen_with_text_field")
    object DieResultWithRollButton : Screens("result_with_roll_button")
    object SixButtonsScreen : Screens("six_buttons_screen")



}
