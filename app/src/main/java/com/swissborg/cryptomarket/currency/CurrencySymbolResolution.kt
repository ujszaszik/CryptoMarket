package com.swissborg.cryptomarket.currency

import com.swissborg.cryptomarket.currency.model.CryptoCurrency
import com.swissborg.cryptomarket.currency.model.Currency
import com.swissborg.cryptomarket.currency.model.HardCurrency
import com.swissborg.cryptomarket.extension.removeColons

data class CurrencyConversionData(
    val from: Currency,
    val to: Currency
)

fun resolveConversionFromSymbol(symbol: String): CurrencyConversionData? {
    if (!symbol.startsWith("t")) return null
    val symbolWithoutPrefix = symbol.substring(1)
    val symbolWithoutSeparator = symbolWithoutPrefix.removeColons()
    val to = HardCurrency.values().firstOrNull { symbolWithoutSeparator.endsWith(it.id) } ?: return null
    val indexToCutUntil = symbolWithoutSeparator.length - to.id.length
    val symbolWithoutPostfix = symbolWithoutSeparator.substring(0, indexToCutUntil)
    val from = CryptoCurrency.values().firstOrNull { symbolWithoutPostfix == it.id } ?: return null
    return CurrencyConversionData(from, to)
}