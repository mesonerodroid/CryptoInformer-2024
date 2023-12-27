package s.mesonero.cryptoinformer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class CryUseCase :CryCommonUseCase<CryptoSimpleUiElement>() {

    private val repository: CryRepository = CryRepository()
    private val _cryFlow = MutableStateFlow<CryState> (CryState.Loading)
    val uiState: StateFlow<CryState> = _cryFlow


    suspend fun getCryptoData (): AppResult<CryptoDataUi> {
        return withContext(Dispatchers.IO) {
            // Blocking network request code
            return@withContext repository.getCry()
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