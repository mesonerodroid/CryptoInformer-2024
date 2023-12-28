package s.mesonero.cryptoinformer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val _cryFlow = MutableStateFlow<CryState> (CryState.Loading)
    val uiState: StateFlow<CryState> = _cryFlow

    private val getCryptoInformationUseCase: GetCryptoInformationUseCase = GetCryptoInformationUseCase()

    fun getCriptoInfo() {
        viewModelScope.launch {
            val result: AppResult<CryptoDataUi> = getCryptoInformationUseCase.getCryptoData()
            Log.e("depuro", "Result "+result)
            result.appError?.let {
                //_cryFlow.update {  }
                _cryFlow.value = CryState.Error(it)
            }
            result.data?.let {
                _cryFlow.value = CryState.Success(it)
            }
        }
    }

    fun getVError() {
        viewModelScope.launch {
            val result: AppResult<CryptoDataUi> = getCryptoInformationUseCase.getTheCryyyError()
            Log.e("depuro", "Result "+result)
            result.appError?.let {
                //_cryFlow.update {  }
                _cryFlow.value = CryState.Error(it)
            }
            result.data?.let {
                _cryFlow.value = CryState.Success(it)
            }
        }
    }

}

data class CryElementState (
    val isLoading: Boolean = true,
    val error: CryAppError? = null,
    val cryptoSimpleUiElement: CryptoSimpleUiElement? = null
)

