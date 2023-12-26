package s.mesonero.cryptoinformer

class CryptoInfoContainerRatesRemote {

    val btc: CryptoInfoRemote? = null
    val eth: CryptoInfoRemote? = null
    val ltc: CryptoInfoRemote? = null
    val bch: CryptoInfoRemote? = null
    val eur: CryptoInfoRemote? = null
    val usd: CryptoInfoRemote? = null

    fun getList(): List<CryptoInfoRemote?> {
        return listOf(btc, eth, ltc, bch, eur, usd)
    }
}
