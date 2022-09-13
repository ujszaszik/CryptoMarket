package com.swissborg.cryptomarket.features.ticker.data.mapper

import com.swissborg.cryptomarket.features.ticker.data.service.TickerDetailResponse
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModel
import com.swissborg.cryptomarket.network.BaseMapper
import com.swissborg.cryptomarket.network.MalformedResponseException

class TickerDetailResponseMapper(private val symbol: String? = null) :
    BaseMapper<TickerDetailResponse, TickerModel> {

    override fun map(remote: TickerDetailResponse): TickerModel {
        var currentIndex = 0
        val currentSymbol = symbol ?: remote[0].toString().also { currentIndex++ }
        return TickerModel(
            symbol = currentSymbol,
            bid = remote.safeUnwrapToDouble(currentIndex++),
            bidSize = remote.safeUnwrapToDouble(currentIndex++),
            lowestAskPrice = remote.safeUnwrapToDouble(currentIndex++),
            askSizeSum = remote.safeUnwrapToDouble(currentIndex++),
            dailyChange = remote.safeUnwrapToDouble(currentIndex++),
            dailyChangeRelative = remote.safeUnwrapToDouble(currentIndex++),
            lastPrice = remote.safeUnwrapToDouble(currentIndex++),
            dailyVolume = remote.safeUnwrapToDouble(currentIndex++),
            dailyHigh = remote.safeUnwrapToDouble(currentIndex++),
            dailyLow = remote.safeUnwrapToDouble(currentIndex++)
        )
    }

    private fun List<Any>.safeUnwrapToDouble(index: Int): Double {
        return try {
            get(index).toString().toDouble()
        } catch (throwable: Throwable) {
            throw MalformedResponseException()
        }
    }
}