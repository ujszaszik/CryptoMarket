package com.swissborg.cryptomarket.features.ticker.data.model

data class TickerModel(
    /** The symbol of the requested ticker data */
    val symbol: String,

    /** average of all fixed rate funding over the last hour (funding tickers only) */
    val flashReturnRate: Double? = null,

    /** Price of last highest bid */
    val bid: Double,

    /** Bid period covered in days (funding tickers only) */
    val bidPeriodDays: Int? = null,

    /** Sum of the 25 highest bid sizes */
    val bidSize: Double,

    /** Price of last lowest ask */
    val lowestAskPrice: Double,

    /** Ask period covered in days (funding tickers only) */
    val askPeriod: Int? = null,

    /** Sum of the 25 lowest ask sizes */
    val askSizeSum: Double,

    /** Amount that the last price has changed since yesterday */
    val dailyChange: Double,

    /** Relative price change since yesterday (*100 for percentage change) */
    val dailyChangeRelative: Double,

    /** Price of the last trade */
    val lastPrice: Double,

    /** Daily volume */
    val dailyVolume: Double,

    /** Daily high */
    val dailyHigh: Double,

    /** Daily low */
    val dailyLow: Double,

    /** The amount of funding that is available at the Flash Return Rate (funding tickers only) */
    val availableFlashReturnRate: Double? = null
)
