package s.mesonero.cryptoinformer

sealed class ComposeRoutes(val route: String) {
    object GreetingScreen: ComposeRoutes("greeting")
    object InformationScreen: ComposeRoutes("information")

}