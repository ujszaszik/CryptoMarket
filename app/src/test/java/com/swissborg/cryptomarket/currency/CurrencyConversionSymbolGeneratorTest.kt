package com.swissborg.cryptomarket.currency

import com.swissborg.cryptomarket.currency.model.CryptoCurrency
import com.swissborg.cryptomarket.currency.model.HardCurrency
import com.swissborg.cryptomarket.extension.empty
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyConversionSymbolGeneratorTest {

    @Test
    fun `test symbol generation, when only from values added, then generated text is empty`() {
        val generatedValue = currencyConversionSymbols {
            from {
                listOf(CryptoCurrency.AAVE, CryptoCurrency.APECOIN, CryptoCurrency.CARDANO)
            }
        }.value
        assertEquals(String.empty, generatedValue)
    }

    @Test
    fun `test symbol generation, when only to values added, then generated text is empty`() {
        val generatedValue = currencyConversionSymbols {
            to {
                listOf(CryptoCurrency.AAVE, CryptoCurrency.APECOIN, CryptoCurrency.CARDANO)
            }
        }.value
        assertEquals(String.empty, generatedValue)
    }

    @Test
    fun `test symbol generation, when single value that is longer then three characters added, then generated text is single and has colons`() {
        val generatedValue = currencyConversionSymbols {
            fromSingle { CryptoCurrency.SWISSBORG }
            toSingle { HardCurrency.USD }
        }.value
        val expectedOutcome = "tCHSB:USD"
        assertEquals(expectedOutcome, generatedValue)
    }

    @Test
    fun `test symbol generation, when single value that is three characters added, then generated text is single and has no colons`() {
        val generatedValue = currencyConversionSymbols {
            fromSingle { CryptoCurrency.BITCOIN }
            toSingle { HardCurrency.USD }
        }.value
        val expectedOutcome = "tBTCUSD"
        assertEquals(expectedOutcome, generatedValue)
    }

    @Test
    fun `test symbol generation, when single from value added with multiple to values, then generated text is appropriate`() {
        val generatedValue = currencyConversionSymbols {
            fromSingle { CryptoCurrency.SWISSBORG }
            to { listOf(HardCurrency.USD, HardCurrency.EUR) }
        }.value
        val expectedOutcome = "tCHSB:USD,tCHSB:EUR"
        assertEquals(expectedOutcome, generatedValue)
    }

    @Test
    fun `test symbol generation, when multiple from value added with single to value, then generated text is appropriate`() {
        val generatedValue = currencyConversionSymbols {
            from { listOf(CryptoCurrency.SWISSBORG, CryptoCurrency.BITCOIN, CryptoCurrency.ETHEREUM) }
            toSingle { HardCurrency.USD }
        }.value
        val expectedOutcome = "tCHSB:USD,tBTCUSD,tETHUSD"
        assertEquals(expectedOutcome, generatedValue)
    }

    @Test
    fun `test symbol generation, when multiple from and to values added, then generated text is appropriate`() {
        val generatedValue = currencyConversionSymbols {
            from { listOf(CryptoCurrency.SWISSBORG, CryptoCurrency.BITCOIN, CryptoCurrency.ETHEREUM) }
            to { listOf(HardCurrency.USD, HardCurrency.EUR) }
        }.value
        val expectedOutcome = "tCHSB:USD,tCHSB:EUR,tBTCUSD,tBTCEUR,tETHUSD,tETHEUR"
        assertEquals(expectedOutcome, generatedValue)
    }

    @Test
    fun `test symbol generation, when no values added, then generated text is empty`() {
        val generatedValue = currencyConversionSymbols {}.value
        assertEquals(String.empty, generatedValue)
    }

}