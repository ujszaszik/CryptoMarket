package com.swissborg.cryptomarket.features.ticker.data.repository

import com.swissborg.cryptomarket.coroutines.ResourceFlow
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModel
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModelList
import com.swissborg.cryptomarket.network.ApiErrorCode
import com.swissborg.cryptomarket.network.Resource
import kotlinx.coroutines.flow.flow

class FakeTickerListRepository : ITickerRepository {
    var throwError = false

    val testModels = listOf(
        TickerModel(
            symbol = "tAAVE:USD",
            bid = 1.0,
            bidSize = 2.0,
            lowestAskPrice = 3.0,
            askSizeSum = 4.0,
            dailyChange = 5.0,
            dailyChangeRelative = 6.0,
            lastPrice = 7.0,
            dailyVolume = 8.0,
            dailyLow = 9.0,
            dailyHigh = 10.0
        ),
        TickerModel(
            symbol = "tBTCUSD",
            bid = 11.0,
            bidSize = 12.0,
            lowestAskPrice = 13.0,
            askSizeSum = 14.0,
            dailyChange = 15.0,
            dailyChangeRelative = 16.0,
            lastPrice = 17.0,
            dailyVolume = 18.0,
            dailyLow = 19.0,
            dailyHigh = 20.0
        ),
        TickerModel(
            symbol = "tCHSB:USD",
            bid = 21.0,
            bidSize = 22.0,
            lowestAskPrice = 23.0,
            askSizeSum = 24.0,
            dailyChange = 25.0,
            dailyChangeRelative = 26.0,
            lastPrice = 27.0,
            dailyVolume = 28.0,
            dailyLow = 29.0,
            dailyHigh = 30.0
        )
    )

    private val testApiError = ApiErrorCode.DEFAULT

    override fun getTickers(params: String): ResourceFlow<TickerModelList> {
        return flow {
            if (throwError) emit(Resource.Error(testApiError.message))
            else emit(Resource.Success(TickerModelList(testModels)))
        }
    }
}