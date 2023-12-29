package s.mesonero.cryptoinformer.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import s.mesonero.cryptoinformer.data.model.CryptoResponseRemoteDTO

interface CryptoServiceInfo {

    @GET("v3/exchange_rates")
    suspend fun getCryptoRates(): Response<CryptoResponseRemoteDTO>
}