package com.swissborg.cryptomarket.features.ticker.data.service

import retrofit2.http.GET
import retrofit2.http.Query

interface TickerService {

    @GET("v2/tickers")
    suspend fun getTickers(@Query("symbols") symbols: String?): TickerListResponse

}