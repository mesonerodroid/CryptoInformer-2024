package s.mesonero.cryptoinformer

import retrofit2.Response
import retrofit2.http.GET

interface CryptoServiceInfo {

    @GET("v3/exchange_rates")
    suspend fun getCryptoRates(): Response<CryptoResponseRemoteDTO>
}