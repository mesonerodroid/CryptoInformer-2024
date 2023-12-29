package s.mesonero.cryptoinformer.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import s.mesonero.cryptoinformer.data.model.AppResult
import s.mesonero.cryptoinformer.ui.state.CryptoState
import s.mesonero.cryptoinformer.domain.GetCryptoInformationUseCase
import s.mesonero.cryptoinformer.ui.model.CryptoDataUi

class CryptoInfoViewModel: ViewModel() {

    private val _cryFlow = MutableStateFlow<CryptoState> (CryptoState.Loading)
    val uiState: StateFlow<CryptoState> = _cryFlow

    private val getCryptoInformationUseCase: GetCryptoInformationUseCase = GetCryptoInformationUseCase()

    fun getCriptoInfo() {
        viewModelScope.launch {
            val result: AppResult<CryptoDataUi> = getCryptoInformationUseCase.getCryptoData()
            Log.e("depuro", "Result "+result)
            result.appError?.let {
                //_cryFlow.update {  }
                _cryFlow.value = CryptoState.Error(it)
            }
            result.data?.let {
                _cryFlow.value = CryptoState.Success(it)
            }
        }
    }

    fun getVError() {
        viewModelScope.launch {
            val result: AppResult<CryptoDataUi> = getCryptoInformationUseCase.getTheCryyyError()
            Log.e("depuro", "Result "+result)
            result.appError?.let {
                //_cryFlow.update {  }
                _cryFlow.value = CryptoState.Error(it)
            }
            result.data?.let {
                _cryFlow.value = CryptoState.Success(it)
            }
        }
    }
}
