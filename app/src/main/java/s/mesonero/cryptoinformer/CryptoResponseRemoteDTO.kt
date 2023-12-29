package s.mesonero.cryptoinformer

import java.text.NumberFormat
import java.util.*

data class CryptoResponseRemoteDTO (
    val rates: CryptoInfoContainerRatesRemote
) {
    fun toCryptoData(): AppResult<CryptoDataUi> {
        val usdRate = rates.getUsdRate()?.value ?: return failureData("server_usd_error")
        val eurRate = rates.getEurRate()?.value ?: return failureData("server_eur_error")

        val bitcoinUsdValue = (usdRate)
        val bitcoinEurValue = (eurRate)

        val list = mutableListOf<CryptoSimpleUiElement>()
        val rates = rates.getList()
        rates.onEach { infoRate ->
            infoRate?.type?.let { type ->
                if (type == "crypto") {
                    val uiData = CryptoSimpleUiElement(
                        infoRate?.name ?: return failureData("no_name"),
                        infoRate.unit ?: return failureData("no_symbol"),
                        infoRate.value,
                        eurFormat(bitcoinEurValue / infoRate.value),
                        usdFormat(bitcoinUsdValue / infoRate.value),
                    )
                    list.add(uiData)
                }
            } ?: return failureData("no_type_specified")
        }
        return AppResult<CryptoDataUi>().apply {
            data = CryptoDataUi(list, rates.size)
        }
    }

    fun failureData(code: String): AppResult<CryptoDataUi> {
        return AppResult<CryptoDataUi>().apply {
            appError = CryAppError().apply { message = code }
        }
    }

    private fun eurFormat(amount: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.GERMAN)
        val currency = Currency.getInstance("EUR")
        format.currency = currency
        return format.format(amount)
    }
    private fun usdFormat(amount: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        val currency = Currency.getInstance("USD")
        format.currency = currency
        return format.format(amount)
    }
}
