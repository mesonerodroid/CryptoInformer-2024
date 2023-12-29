package s.mesonero.cryptoinformer.ui.model

data class CryptoSimpleUiElement (
    val name: String = "",
    val shortName: String = "",
    val rateBtc: Double = 0.0,
    val rateEur: String = "",
    val rateUsd: String = ""
)