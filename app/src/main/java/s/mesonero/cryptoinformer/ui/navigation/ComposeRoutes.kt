package s.mesonero.cryptoinformer.ui.navigation

sealed class ComposeRoutes(val route: String) {
    object GreetingScreen: ComposeRoutes("greeting")
    object InformationScreen: ComposeRoutes("information")

}