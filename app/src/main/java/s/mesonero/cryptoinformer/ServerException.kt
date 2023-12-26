package s.mesonero.cryptoinformer

class ServerException (val errorCode: String? = null): Throwable() {
    override val message: String?
        get() = errorCode
}