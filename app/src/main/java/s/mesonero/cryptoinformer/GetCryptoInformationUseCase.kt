package s.mesonero.cryptoinformer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCryptoInformationUseCase :CryCommonUseCase< AppResult<CryptoDataUi>>() { // JUST CHANGE TO IO THREAD

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

    /*
    fun bla(): <AppResult<CryUiElement>  {

        val ret = repository.getCry()
        /*
        return repository.getCry().flatMapConcat { result ->  // aqui seria inncesario,

            result.data?.let {
                flow { emit(it) }
            } ?: run {
                result.appError.let {

                }
            }
        }

         */
    }

     */
}