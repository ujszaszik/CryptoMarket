package com.swissborg.cryptomarket.currency

import com.swissborg.cryptomarket.currency.model.CryptoCurrency
import com.swissborg.cryptomarket.currency.model.HardCurrency
import com.swissborg.cryptomarket.extension.empty
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencySymbolResolutionTest {

    @Test
    fun `test symbol resolution, when empty string provided, then returned value is null`() {
        val resolvedSymbol = resolveConversionFromSymbol(String.empty)
        assertNull(resolvedSymbol)
    }

    @Test
    fun `test symbol resolution, when string does not start with letter t, then returned value is null`() {
        val resolvedSymbol = resolveConversionFromSymbol("CHSB:USD")
        assertNull(resolvedSymbol)
    }

    @Test
    fun `test symbol resolution, when invalid from currency provided, then returned value is null`() {
        val resolvedSymbol = resolveConversionFromSymbol("tUNKNOWN:USD")
        assertNull(resolvedSymbol)
    }

    @Test
    fun `test symbol resolution, when from currency is not crypto, then returned value is null`() {
        val resolvedSymbol = resolveConversionFromSymbol("tEURUSD")
        assertNull(resolvedSymbol)
    }

    @Test
    fun `test symbol resolution, when invalid to currency provided, then returned value is null`() {
        val resolvedSymbol = resolveConversionFromSymbol("tCHSB:UNKNWON")
        assertNull(resolvedSymbol)
    }

    @Test
    fun `test symbol resolution, when to currency is not hard, then returned value is null`() {
        val resolvedSymbol = resolveConversionFromSymbol("tCHSB:BTC")
        assertNull(resolvedSymbol)
    }

    @Test
    fun `test symbol resolution, when values are valid, then returned from value is parsed appropriately`() {
        val resolvedSymbol = resolveConversionFromSymbol("tCHSB:USD")
        assertEquals(CryptoCurrency.SWISSBORG, resolvedSymbol?.from)
    }

    @Test
    fun `test symbol resolution, when values are valid, then returned to value is parsed appropriately`() {
        val resolvedSymbol = resolveConversionFromSymbol("tCHSB:USD")
        assertEquals(HardCurrency.USD, resolvedSymbol?.to)
    }

}