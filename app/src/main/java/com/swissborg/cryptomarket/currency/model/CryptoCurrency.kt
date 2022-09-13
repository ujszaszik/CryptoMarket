package com.swissborg.cryptomarket.currency.model

enum class CryptoCurrency(
    override val id: String,
    override val displayName: String
) : Currency {
    AAVE("AAVE", "Aave"),
    APECOIN("APE", "ApeCoin"),
    AVALANCHE("AVAX", "Avalanche"),
    BITCOIN("BTC", "Bitcoin"),
    CARDANO("ADA", "Cardano"),
    DAI("DAI", "Dai"),
    DASH("DSH", "Dashcoin"),
    DOGECOIN("DOGE", "Dogecoin"),
    EOS("EOS", "EOS"),
    ETHEREUM("ETH", "Ethereum"),
    FANTOM("FTM", "Fantom"),
    FILECOIN("FIL", "Filecoin"),
    LITECOIN("LTC", "Litecoin"),
    MONERO("XMR", "Monero"),
    NEO("NEO", "Neo"),
    NEXO("NEXO", "Nexo"),
    OMG("OMG", "OMG Network"),
    PLUTON("PLU", "Pluton"),
    POLKADOT("DOT", "Polkadot"),
    POLYGON("MATIC", "Polygon"),
    RIPPLE("XRP", "XRP"),
    SHIBA_INU("SHIB", "Shiba Inu"),
    SOLANA("SOL", "Solana"),
    STATUS("SNT", "Status"),
    STELLAR("XLM", "Stellar"),
    SWISSBORG("CHSB", "SwissBorg"),
    TERRA("LUNA", "Terra"),
    TRON("TRX", "TRON"),
    UNISAWP("UNI", "Uniswap"),
    ZETACOIN("ZEC", "Zetacoin");
}