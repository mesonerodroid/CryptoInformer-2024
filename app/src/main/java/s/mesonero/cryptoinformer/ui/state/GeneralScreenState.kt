package s.mesonero.cryptoinformer.ui.state

sealed class GeneralScreenState{
    object Loading : GeneralScreenState()
    class Error(val message: String) : GeneralScreenState()
    data class Success(val data: Any): GeneralScreenState()
}