package s.mesonero.cryptoinformer.ui.unused_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.compose.AppTheme
import s.mesonero.cryptoinformer.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GreetingComposeFragment : Fragment() {



    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        theFragment()
                    }
                }
            }
        }
    }

    @Composable
    fun theFragment() {
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
                val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
                /*
                Image(
                    painter = painter,
                    contentDescription = "imageSplash",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(imageRatio)
                )
                 */
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
                    text = "Bienvenido a CryptoInformer", fontFamily = FontFamily.SansSerif,
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
                    text = "Esta aplicación muestra el valor y la cotización en tiempo real de las criptomonedas más importantes",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
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
                   // text = "https://www.coingecko.com/es", fontFamily = FontFamily.SansSerif,
                    text = "", fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Blue
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 5.dp, 25.dp, 5.dp)
                    .constrainAs(button) {
                        top.linkTo(link.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            )
            {
                Button(onClick = {
                    findNavController().navigate(R.id.cryptoInformationFragment)
                },
                    Modifier
                        .fillMaxWidth(0.95f)
                        .padding(25.dp)) {
                    Text(text = "ENTRAR")
                }
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        AppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                theFragment()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

}