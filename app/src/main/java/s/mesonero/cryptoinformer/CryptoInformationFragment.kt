package s.mesonero.cryptoinformer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose.AppTheme
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CryptoInformationFragment : Fragment() {

    val cryptoInfoViewModel: CryptoInfoViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Composable
    fun theFragment() {

        var list = listOf<CryptoSimpleUiElement>(
            CryptoSimpleUiElement("Uno", "BTC", 344.67, "12578.98€", "13567.34$"),
            CryptoSimpleUiElement("Dos", "ETH", 34434.77, "12578.98€", "13567.34$"),
            CryptoSimpleUiElement("Tres", "XRP", 14344.67, "12578.98€", "13567.34$"),
            CryptoSimpleUiElement("Cuatro", "DOT", 14344.67, "12578.98€", "13567.34$"),
            CryptoSimpleUiElement("Cinco", "BCH", 14.77, "12578.98€", "13567.34$") ,
            CryptoSimpleUiElement("Seis", "LINK", 5.12, "12578.98€", "13567.34$")
        )

        ShowList(list)

    }

    private @Composable
    fun ShowList(list: List<CryptoSimpleUiElement>) {


        CircularProgressIndicator()
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {

            Image(
                painter = painterResource(id = R.drawable.bitcoin_splash),
                contentDescription = "test",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(2.dp, 35.dp, 2.dp, 20.dp)
                    .border(
                        BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                        RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(16.dp)),

                contentScale = ContentScale.Fit,
            )

            Image(
                painter = painterResource(id = R.drawable.reload2),
                contentDescription = "test",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(2.dp, 3.dp, 2.dp, 3.dp),

                contentScale = ContentScale.Fit,
            )

            Box(
                modifier = Modifier
                    .padding(12.dp, 10.dp, 12.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(16.dp),
                        ),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    itemsIndexed(list) { index, item ->
                        HolderCryptoCurrency(item)
                        if (index < list.lastIndex)
                            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
                    }
                }
            }

        }
    }

    private @Composable
    fun HolderCryptoCurrency(it: CryptoSimpleUiElement) {
        Log.e("depuro", "holder crypto")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.Blue
            ),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable {
                        Log.e("depuro", "click " + it)
                    }

            ) {
                CryptoIconImage(it.shortName)

                Column( modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(horizontal = 3.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )

                    Text(
                        text = it.rateEur,
                        modifier = Modifier.padding(horizontal = 2.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                }
            }
        }

    }

    @Composable
    fun CryptoIconImage(shortName: String) {
        var drawable = 0
        when (shortName) {
            "BTC" -> drawable = R.drawable.btc2
            "ETH" -> drawable = R.drawable.eth2
            "XRP" -> drawable = R.drawable.xrp
            "DOT" -> drawable = R.drawable.dot2
            "BCH" -> drawable = R.drawable.bch
            "LINK" -> drawable = R.drawable.link2
        }
        
        Box (modifier = Modifier
            .background(Color.White, shape = CircleShape)
            .size(52.dp, 52.dp)
            .clip(CircleShape),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "test",
                modifier = Modifier
                    .size(37.dp, 37.dp),
                contentScale = ContentScale.Crop
            )
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
        collectFlow()
        //myViewModel.getVCry()
    }

    private fun collectFlow() {
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                cryptoInfoViewModel.uiState.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is CryState.Success -> showUiData(uiState.cryptoDataUi)
                        is CryState.Error -> showError(uiState.appError)
                        is CryState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    @Composable
    fun CenteredCircularProgressIndicator(visible: Boolean) = CircularProgressIndicator(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )

    @Composable
    fun IndeterminateCircularIndicator(state: Boolean) {
        var loading by remember { mutableStateOf(state) }

        if (state) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        if (!loading) return


    }

    private fun showLoading() {
        Log.e("depuro", "show loading")
    }
    private fun hideLoading() {
    }

    private fun showError(error: CryAppError) {
        Log.e("depuro", "show error " + error)

    }

    private fun showUiData(it: CryptoDataUi) {
        Log.e("depuro", "show ui data " + it)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}