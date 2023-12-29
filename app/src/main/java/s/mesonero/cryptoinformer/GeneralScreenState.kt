package s.mesonero.cryptoinformer

sealed class GeneralScreenState{
    object Loading : GeneralScreenState()
    class Error(val message: String) : GeneralScreenState()
    data class Success(val data: Any): GeneralScreenState()
    /* we are using Any data type for the Success class to make it compatible
     with multiple network calls */
}