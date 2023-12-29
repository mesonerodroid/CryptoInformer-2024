package s.mesonero.cryptoinformer.data.model

import s.mesonero.cryptoinformer.ui.model.CryptoDataUi
import s.mesonero.cryptoinformer.ui.state.CryAppError

class AppResult<T> {
    var appError: CryAppError? = null
    var data:T? = null
}


fun appErrorResult(desc: String): AppResult<CryptoDataUi> {
    return AppResult<CryptoDataUi>().apply {
        appError = CryAppError().apply {
            message = desc
        }
    }
}

/*


sealed class AppResult<T> {

    data class AppError(val messageee: String)
    data class Data<T>(val data: Data<T>)
}
 */