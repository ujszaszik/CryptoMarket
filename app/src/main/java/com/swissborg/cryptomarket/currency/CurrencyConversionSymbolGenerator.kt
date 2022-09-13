package com.swissborg.cryptomarket.currency

import com.swissborg.cryptomarket.currency.model.CryptoCurrency
import com.swissborg.cryptomarket.currency.model.Currency
import com.swissborg.cryptomarket.currency.model.HardCurrency
import com.swissborg.cryptomarket.extension.comma
import java.util.*

/**
 * Kotlin DSL for conversion symbols generation
 */
class CurrencyConversionSymbolGenerator {
    private val currenciesFrom = mutableListOf<Currency>()
    private val currenciesTo = mutableListOf<Currency>()

    @JvmInline
    value class Value(val value: String)

    private fun generateText(): Value {
        return Value(
            StringJoiner(String.comma).apply {
                currenciesFrom.forEach { from ->
                    currenciesTo.forEach { to ->
                        add("${from.symbolPrefix()}${to.id}")
                    }
                }
            }.toString()
        )
    }

    inner class Scope {
        fun from(block: () -> List<Currency>) = currenciesFrom.addAll(block())
        fun fromSingle(element: () -> Currency) = currenciesFrom.add(element())
        fun fromAllCrypto() = currenciesFrom.addAll(CryptoCurrency.values())
        fun fromAllHard() = currenciesFrom.addAll(HardCurrency.values())
        fun to(block: () -> List<Currency>) = currenciesTo.addAll(block())
        fun toSingle(element: () -> Currency) = currenciesTo.add(element())
        fun toAllCrypto() = currenciesTo.addAll(CryptoCurrency.values())
        fun toAllHard() = currenciesTo.addAll(HardCurrency.values())
        fun value() = generateText()
    }
}

fun currencyConversionSymbols(
    block: CurrencyConversionSymbolGenerator.Scope.() -> Unit
): CurrencyConversionSymbolGenerator.Value {
    return CurrencyConversionSymbolGenerator().Scope().also { block(it) }.value()
}