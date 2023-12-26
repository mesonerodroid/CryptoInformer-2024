package s.mesonero.cryptoinformer

class CryptoRemoteDataSourceImpl {
    suspend fun getCryptoData(): AppResult<CryptoDataUi> {
        RetrofitBuilder().createCryptoService().getCryptoRates().let { response ->
            if (response.isSuccessful) return response.body()?.toCryptoData() ?: run {
                return appErrorResult("no_server_response")
            } else return appErrorResult("unsuccessful_response")

        }
    }
}