package com.swissborg.cryptomarket.features.ticker.data.mapper

import com.swissborg.cryptomarket.features.ticker.data.service.TickerListResponse
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModel
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModelList
import com.swissborg.cryptomarket.network.BaseMapper

object TickerListResponseMapper : BaseMapper<TickerListResponse, TickerModelList> {

    override fun map(remote: TickerListResponse): TickerModelList {
        val result = mutableListOf<TickerModel>()
        remote.forEach {
            result.add(TickerDetailResponseMapper().map(it))
        }
        return TickerModelList(result)
    }
}