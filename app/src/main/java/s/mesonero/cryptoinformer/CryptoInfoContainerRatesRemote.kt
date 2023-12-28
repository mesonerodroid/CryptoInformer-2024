package s.mesonero.cryptoinformer

class CryptoInfoContainerRatesRemote {

    val btc: CryptoInfoRemote? = null
    val eth: CryptoInfoRemote? = null
    val xrp: CryptoInfoRemote? = null
    val bch: CryptoInfoRemote? = null
    val link: CryptoInfoRemote? = null
    val dot:  CryptoInfoRemote? = null
    val ltc: CryptoInfoRemote? = null
    val eur: CryptoInfoRemote? = null
    val usd: CryptoInfoRemote? = null

    fun getList(): List<CryptoInfoRemote?> {
        return listOf(btc, eth, xrp, bch, link, dot, ltc, eur, usd)
    }

    fun getUsdRate(): CryptoInfoRemote? {
        return usd
    }

    fun getEurRate(): CryptoInfoRemote? {
        return eur
    }
}
