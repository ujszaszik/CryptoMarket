package com.swissborg.cryptomarket.features.ticker.presentation.ui

import androidx.compose.ui.graphics.Color
import com.swissborg.cryptomarket.compose.screen.filteredlist.Identifiable
import com.swissborg.cryptomarket.currency.model.Currency
import com.swissborg.cryptomarket.extension.asFormattedText
import com.swissborg.cryptomarket.resources.Colors

data class TickerListUiModel(
    val currency: Currency,
    val conversionTo: Currency,
    val iconId: Int,
    val price: Double,
    val changePercent: Double
) : Identifiable {
    override val id = currency.id
}

fun TickerListUiModel.asConversionRateText(): String {
    return "${conversionTo.displayName} ${price.asFormattedText()}"
}

fun TickerListUiModel.changePercentText(): String {
    var defaultText = "${changePercent.asFormattedText()} %"
    if (changePercent > 0) defaultText = "+$defaultText"
    return defaultText
}

fun TickerListUiModel.changePercentColor(): Color {
    return if (changePercent > 0) Colors.green else Colors.red
}