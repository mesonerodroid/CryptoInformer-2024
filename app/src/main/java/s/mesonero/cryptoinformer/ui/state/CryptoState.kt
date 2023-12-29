package s.mesonero.cryptoinformer.ui.state

import s.mesonero.cryptoinformer.ui.model.CryptoDataUi

sealed class CryptoState {
    object Loading: CryptoState()
    data class Success (val cryptoDataUi: CryptoDataUi) : CryptoState()
    data class Error (val appError: CryAppError) : CryptoState()
}
