package s.mesonero.cryptoinformer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import s.mesonero.cryptoinformer.ui.CryptoInfoViewModel
import s.mesonero.cryptoinformer.ui.navigation.ComposeRoutes
import s.mesonero.cryptoinformer.ui.screen.CryptoInformationScreen
import s.mesonero.cryptoinformer.ui.screen.GreetingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: CryptoInfoViewModel by viewModels()
        setContent{
            AppTheme {
                Surface (color = MaterialTheme.colorScheme.background) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = ComposeRoutes.GreetingScreen.route) {
                        composable(ComposeRoutes.GreetingScreen.route) {
                            GreetingScreen(navigationController)
                        }
                        composable(ComposeRoutes.InformationScreen.route) {
                            CryptoInformationScreen(viewModel)
                        }
                    }
                }
            }
        }
    }

}