package com.swissborg.cryptomarket.extension

import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NumericExtensionsTest {

    @Test
    fun `test int or zero, when value is not null, then it is returned`() {
        val test = 1
        assertTrue(test == test.orZero())
    }

    @Test
    fun `test int or zero, when value is null, then zero it is returned`() {
        val test: Int? = null
        assertTrue(0 == test.orZero())
    }

}