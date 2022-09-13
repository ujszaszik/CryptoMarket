package com.swissborg.cryptomarket.extension

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDateTime

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DateTimeExtensionsTest {

    @Test
    fun `test local date time, when format called for time with no one digit value, then value is appropriately formatted`() {
        val testDate = LocalDateTime.of(2022, 11, 11, 22, 45, 30)
        val expectedOutcome = "2022.11.11. 22:45:30"
        assertEquals(expectedOutcome, testDate.asFormattedText())
    }

    @Test
    fun `test local date time, when format called for time with one digit values, then value is appropriately formatted`() {
        val testDate = LocalDateTime.of(2022, 9, 1, 2, 5, 0)
        val expectedOutcome = "2022.09.01. 02:05:00"
        assertEquals(expectedOutcome, testDate.asFormattedText())
    }

}