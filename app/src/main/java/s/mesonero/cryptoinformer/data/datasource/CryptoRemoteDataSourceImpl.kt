package s.mesonero.cryptoinformer.data.datasource

import s.mesonero.cryptoinformer.data.model.AppResult
import s.mesonero.cryptoinformer.data.retrofit.RetrofitBuilder
import s.mesonero.cryptoinformer.data.model.appErrorResult
import s.mesonero.cryptoinformer.ui.model.CryptoDataUi

class CryptoRemoteDataSourceImpl {
    suspend fun getCryptoData(): AppResult<CryptoDataUi> {
        RetrofitBuilder().createCryptoService().getCryptoRates().let { response ->
            if (response.isSuccessful) return response.body()?.toCryptoData() ?: run {
                return appErrorResult("no_server_response")
            } else return appErrorResult("unsuccessful_response")
        }
    }
}