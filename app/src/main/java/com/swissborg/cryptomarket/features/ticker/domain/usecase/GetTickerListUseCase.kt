package com.swissborg.cryptomarket.features.ticker.domain.usecase

import com.swissborg.cryptomarket.coroutines.mapResource
import com.swissborg.cryptomarket.currency.CurrencyConversionSymbolGenerator
import com.swissborg.cryptomarket.features.ticker.data.repository.ITickerRepository
import com.swissborg.cryptomarket.features.ticker.domain.mapper.TickerListUiModelMapper
import javax.inject.Inject

class GetTickerListUseCase @Inject constructor(
    private val repository: ITickerRepository,
    private val mapper: TickerListUiModelMapper
) {

    operator fun invoke(symbols: CurrencyConversionSymbolGenerator.Value) =
        repository
            .getTickers(symbols.value)
            .mapResource { mapper.map(it) }
}