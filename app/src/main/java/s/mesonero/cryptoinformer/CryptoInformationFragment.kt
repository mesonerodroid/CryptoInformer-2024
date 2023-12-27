package s.mesonero.cryptoinformer

import android.content.res.Resources.Theme
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    val myViewModel: MyViewModel by viewModels()

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
            CryptoSimpleUiElement("Uno", "$", 344.67),
            CryptoSimpleUiElement("Dos", "€", 34434.77),
            CryptoSimpleUiElement("Tres", "J", 14344.67),
            CryptoSimpleUiElement("Cuatro", "H", 14344.67),
            CryptoSimpleUiElement("Cinco", "N", 14.77) ,
            CryptoSimpleUiElement("Seis", "B", 5.12)
        )

        ShowList(list)

    }

    private @Composable
    fun ShowList(list: List<CryptoSimpleUiElement>) {

        Column(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
            Image(
                painter = painterResource(id = R.drawable.bitcoin_splash),
                contentDescription = "test",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 35.dp, 2.dp, 60.dp)
                    .border(
                        BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                        RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(16.dp)),

                contentScale = ContentScale.FillWidth,
            )
            Box(
                modifier = Modifier
                    .padding(16.dp, 10.dp, 16.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(16.dp),
                        ),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
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
                Text(
                    text = it.symbol,
                    modifier = Modifier.fillMaxWidth(0.1f),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .padding(horizontal = 3.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                )

                Text(
                    text = it.change.toString(),
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(horizontal = 2.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp
                )
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
                myViewModel.uiState.collect { uiState ->
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

    private fun showLoading() {
        Log.e("depuro", "show loading")
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