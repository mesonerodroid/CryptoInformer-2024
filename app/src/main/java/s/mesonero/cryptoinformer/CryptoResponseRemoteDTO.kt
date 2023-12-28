package s.mesonero.cryptoinformer

import java.text.NumberFormat

data class CryptoResponseRemoteDTO (
    val rates: CryptoInfoContainerRatesRemote
) {
    fun toCryptoData(): AppResult<CryptoDataUi> {
        val usdRate = rates.getUsdRate()?.value ?: return failureData("server_usd_error")
        val eurRate = rates.getEurRate()?.value ?: return failureData("server_eur_error")

        val rateConversionUsd = (1 / usdRate)
        val rateConversionEur = (1 / eurRate)

        val list = mutableListOf<CryptoSimpleUiElement>()
        val rates = rates.getList()
        rates.onEach {
            val uiData = CryptoSimpleUiElement(
                it?.name ?: return failureData("no_name"),
                it.unit ?: return failureData("no_symbol"),
                it.value,
                eurFormat (it.value * rateConversionEur),
                usdFormat (it.value * rateConversionUsd))
            list.add(uiData)
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
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        return (format.format(format) + "€")
    }
    private fun usdFormat(amount: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        return (format.format(format) + "$")
    }
}
