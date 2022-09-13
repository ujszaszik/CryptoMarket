package com.swissborg.cryptomarket.features.ticker.domain.mapper

import android.content.Context
import com.swissborg.cryptomarket.currency.resolveConversionFromSymbol
import com.swissborg.cryptomarket.extension.asPercentage
import com.swissborg.cryptomarket.extension.getDrawableIdByName
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModelList
import com.swissborg.cryptomarket.features.ticker.presentation.ui.TickerListUiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

typealias TickerUiModels = List<TickerListUiModel>

class TickerListUiModelMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun map(data: TickerModelList): TickerUiModels {
        val result = mutableListOf<TickerListUiModel>()
        data.values.forEach { ticker ->
            val resolvedConversion = resolveConversionFromSymbol(ticker.symbol)
            resolvedConversion?.let {
                result.add(
                    TickerListUiModel(
                        currency = resolvedConversion.from,
                        conversionTo = resolvedConversion.to,
                        iconId = context.getDrawableIdByName(resolvedConversion.from.id.lowercase()),
                        price = ticker.lastPrice,
                        changePercent = ticker.dailyChangeRelative.asPercentage()
                    )
                )
            }
        }
        return result
    }
}