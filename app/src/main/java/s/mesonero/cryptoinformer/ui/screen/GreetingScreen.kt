package s.mesonero.cryptoinformer.ui.screen

import androidx.fragment.app.Fragment
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.compose.AppTheme
import s.mesonero.cryptoinformer.ui.navigation.ComposeRoutes
import s.mesonero.cryptoinformer.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@Composable
fun GreetingScreen(navigationController: NavHostController) {

    val navigationController = navigationController
    // This property is only valid between onCreateView and
    // onDestroyView.

    Greeting(navigationController)

}

    @Composable
    fun Greeting(navigationController: NavHostController? = null) {
        ConstraintLayout(Modifier.fillMaxSize()) {

            val (bigImage, bigTitleText, descriptionText, link, button) = createRefs()
            val midGuide = createGuidelineFromBottom(0.5f)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .constrainAs(bigImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(midGuide)
                }) {

                val painter = painterResource(id = R.drawable.bitcoin_splash)
                Image(
                    painter = painterResource(id = R.drawable.bitcoin_splash),
                    contentDescription = "test",
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp)),

                    contentScale = ContentScale.FillWidth,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .constrainAs(bigTitleText) {
                        top.linkTo(bigImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = stringResource(R.string.welcome), fontFamily = FontFamily.SansSerif,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .constrainAs(descriptionText) {
                        top.linkTo(bigTitleText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = stringResource(R.string.welcome_body),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            /*
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 5.dp, 25.dp, 5.dp)
                    .constrainAs(link) {
                        top.linkTo(descriptionText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "https://www.coingecko.com/es", fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Blue
                )
            }

             */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 5.dp, 25.dp, 5.dp)
                    .constrainAs(button) {
                        top.linkTo(descriptionText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            )
            {
                Button(onClick = {
                    //navegar
                    navigationController?.navigate(ComposeRoutes.InformationScreen.route)
                },
                    Modifier
                        .fillMaxWidth(0.95f)
                        .padding(25.dp)) {
                    Text(text = stringResource(R.string.enter))
                }
            }

        }
    }
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting()
        }
    }
}
