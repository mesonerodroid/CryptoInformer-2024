package s.mesonero.cryptoinformer.data.retrofit


import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private var urlServer = "https://api.coingecko.com/api/"
    private var retrofitBuilder: Retrofit
    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logInterceptor)

        retrofitBuilder = Retrofit.Builder()
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(urlServer).build()
    }

    fun createCryptoService(): CryptoServiceInfo {
        return retrofitBuilder.create(CryptoServiceInfo::class.java)
    }
}