package com.swissborg.cryptomarket.features.ticker.data.repository

import com.swissborg.cryptomarket.coroutines.ResourceFlow
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModelList

interface ITickerRepository {

    fun getTickers(params: String): ResourceFlow<TickerModelList>
}