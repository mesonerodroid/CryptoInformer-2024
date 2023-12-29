package s.mesonero.cryptoinformer.data.repository

import s.mesonero.cryptoinformer.data.datasource.CryptoRemoteDataSourceImpl
import s.mesonero.cryptoinformer.data.model.AppResult
import s.mesonero.cryptoinformer.ui.state.CryAppError
import s.mesonero.cryptoinformer.ui.model.CryptoDataUi

class CryptoRepository {
    val cryptoRemoteDataSourceImpl = CryptoRemoteDataSourceImpl()

    suspend fun getRepoCryptoData(): AppResult<CryptoDataUi> {
        return cryptoRemoteDataSourceImpl.getCryptoData()
    }

    fun getCryError(): AppResult<CryptoDataUi> {
        val result = AppResult<CryptoDataUi>()
        result.appError = CryAppError()
        result.appError?.message = "El error"
        return (result)
    }


    /*
    fun getCryEmpty(): Flow<CryUiElement> =
        flow {
            val element = CryUiElement(name = "", "$", 34.78f)
            emit(element)
            val coroutineContext:CoroutineContext
        }

    fun getCryeeeEmpty(): Flow<CryUiElement> =
        flow {
            val element = CryUiElement(name = "", "$", 34.78f)
            val error = CryAppError()
            error.message = "Mensaje de error"
            emit(element)
        }
    fun getCryWithError(): CryAppError {
        val error = CryAppError()
        error.message = "Mensaje de error"
        return error
    }

     */
}
