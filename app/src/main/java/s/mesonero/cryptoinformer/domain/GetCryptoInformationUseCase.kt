package s.mesonero.cryptoinformer.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import s.mesonero.cryptoinformer.data.model.AppResult
import s.mesonero.cryptoinformer.ui.model.CryptoDataUi
import s.mesonero.cryptoinformer.data.repository.CryptoRepository

class GetCryptoInformationUseCase : CryCommonUseCase<AppResult<CryptoDataUi>>() { // JUST CHANGE TO IO THREAD

    private val repository: CryptoRepository = CryptoRepository()

    suspend fun getCryptoData (): AppResult<CryptoDataUi> {
        //runTask (repository.getRepoCryptoData() )
        return withContext(Dispatchers.IO) {
            return@withContext repository.getRepoCryptoData()
        }
    }

    suspend fun getTheCryyyError (): AppResult<CryptoDataUi> {
        return withContext(Dispatchers.IO) {
            // Blocking network request code
            return@withContext repository.getCryError()
        }
    }
}