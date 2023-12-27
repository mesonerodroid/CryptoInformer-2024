package s.mesonero.cryptoinformer

data class CryptoResponseRemoteDTO (
    val rates: CryptoInfoContainerRatesRemote
) {
    fun toCryptoData(): AppResult<CryptoDataUi> {
        val list = mutableListOf<CryptoSimpleUiElement>()
        val rates = rates.getList()
        rates.onEach {
            val uiData = CryptoSimpleUiElement(
                it?.name ?: return failureData("no_name"),
                it.unit ?: return failureData("no_symbol"),
                it.value)
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
}
