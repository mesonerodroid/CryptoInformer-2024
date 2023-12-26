package s.mesonero.cryptoinformer

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