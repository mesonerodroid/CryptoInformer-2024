package s.mesonero.cryptoinformer

import android.util.Log
import androidx.fragment.app.Fragment
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose.AppTheme

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */


@Composable
fun CryptoInformationScreen(cryptoViewModel: CryptoInfoViewModel) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<CryState>(
        initialValue = CryState.Loading,
        key1 = lifecycle,
        key2 = cryptoViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            cryptoViewModel.uiState.collect { value = it }
        }
    }

    Log.e("depuro", "uiState " + uiState)
    when (uiState) {
        is CryState.Success -> ShowList((uiState as CryState.Success).cryptoDataUi.list)
        is CryState.Error -> showError((uiState as CryState.Error).appError)
        CryState.Loading -> CenteredCircularProgressIndicator()
    }
    LaunchedEffect(cryptoViewModel) {
        cryptoViewModel.getCriptoInfo()
    }
}

private @Composable
fun ShowList(list: List<CryptoSimpleUiElement>) {
    var listReference by remember {
        mutableStateOf(
            getCryptoShowList(
                list,
                Constants.FiatDescriptor.EUR.descriptor
            )
        )
    }
    CircularProgressIndicator()
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
        Image(
            painter = painterResource(id = R.drawable.bitcoin_splash),
            contentDescription = "test",
            modifier = Modifier
                .fillMaxHeight(0.27f)
                .padding(2.dp, 35.dp, 2.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp)),

            contentScale = ContentScale.Crop,
        )

        /*
        Image(
            painter = painterResource(id = R.drawable.reload3),
            contentDescription = "test",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(2.dp, 3.dp, 2.dp, 3.dp),

            contentScale = ContentScale.Fit,
        )
         */

        ToggleStringButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            currentSelection = Constants.FiatDescriptor.EUR.descriptor,
            toggleStates = listOf(
                Constants.FiatDescriptor.EUR.descriptor,
                Constants.FiatDescriptor.USD.descriptor
            ),
            onToggleChange = {
                Log.e("depuro", "togle change "+it)
                listReference = getCryptoShowList(list, it)
            })

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
                itemsIndexed(listReference) { index, item ->
                    HolderCryptoCurrency(item)
                    if (index < list.lastIndex)
                        Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
                }
            }
        }

    }
}

fun getCryptoShowList(
    list: List<CryptoSimpleUiElement>,
    fiatDescriptor: String
): List<CryptoShowUiElement> {
    val showList: MutableList<CryptoShowUiElement> = mutableListOf()
    list.forEach {
        if (fiatDescriptor == Constants.FiatDescriptor.EUR.descriptor) {
            showList.add(CryptoShowUiElement(it.name, it.shortName, it.rateBtc, it.rateEur))
        } else {
            showList.add(CryptoShowUiElement(it.name, it.shortName, it.rateBtc, it.rateUsd))
        }
    }
    return showList
}

private @Composable
fun HolderCryptoCurrency(it: CryptoShowUiElement) {
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

            Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                Text(
                    text = it.name,
                    modifier = Modifier.padding(horizontal = 3.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                Text(
                    text = it.rateFiat,
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

    Box(
        modifier = Modifier
            .background(Color.White, shape = CircleShape)
            .size(52.dp, 52.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (drawable != 0) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "test",
                modifier = Modifier
                    .size(37.dp, 37.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InformationPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            var list = listOf<CryptoSimpleUiElement>(
                CryptoSimpleUiElement("Uno", "BTC", 344.67, "12578.98€", "13567.34$"),
                CryptoSimpleUiElement("Dos", "ETH", 34434.77, "12578.98€", "13567.34$"),
                CryptoSimpleUiElement("Tres", "XRP", 14344.67, "12578.98€", "13567.34$"),
                CryptoSimpleUiElement("Cuatro", "DOT", 14344.67, "12578.98€", "13567.34$"),
                CryptoSimpleUiElement("Cinco", "BCH", 14.77, "12578.98€", "13567.34$"),
                CryptoSimpleUiElement("Seis", "LINK", 5.12, "12578.98€", "13567.34$")
            )
            ShowList(list)
        }
    }
}

@Composable
fun CenteredCircularProgressIndicator() = CircularProgressIndicator(
    Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
)

data class CryptoShowUiElement(
    val name: String = "",
    val shortName: String = "",
    val rateBtc: Double = 0.0,
    val rateFiat: String = ""
)

private fun showError(error: CryAppError) {
    Log.e("depuro", "show error " + error)
}

/*
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
 */