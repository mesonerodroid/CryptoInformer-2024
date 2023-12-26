package s.mesonero.cryptoinformer

sealed class CryState {
    object Loading: CryState()
    data class Success (val cryptoDataUi: CryptoDataUi) : CryState()
    data class Error (val appError: CryAppError) : CryState()
}


/*

sealed interface CryState {

    data object Loading : CryState

    data class Success (
        val cryUiElement: CryUiElement
    ) : CryState

    data class Error (val message: String)

    data object LogUserIn : CryState

}
 */

/*
When should you use a data class, sealed interface/class or a combination of both?

Use a sealed interface/class when the screen can be in multiple exclusive states.
Use a data class when the data inside it can mutate. This is particularly useful in screens following an offline-fist approach where the screen might simultaneously display loading indicators, data, and error messages.
*/