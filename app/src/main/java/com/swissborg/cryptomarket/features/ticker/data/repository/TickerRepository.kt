package com.swissborg.cryptomarket.features.ticker.data.repository

import com.swissborg.cryptomarket.coroutines.ResourceFlow
import com.swissborg.cryptomarket.features.ticker.data.mapper.TickerListResponseMapper
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModelList
import com.swissborg.cryptomarket.features.ticker.data.service.TickerService
import com.swissborg.cryptomarket.network.networkFlow
import javax.inject.Inject

class TickerRepository @Inject constructor(
    private val service: TickerService
) : ITickerRepository {

    override fun getTickers(params: String): ResourceFlow<TickerModelList> {
        return networkFlow(
            call = { service.getTickers(params) },
            mapper = TickerListResponseMapper
        )
    }
}