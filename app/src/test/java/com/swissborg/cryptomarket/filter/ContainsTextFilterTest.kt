package com.swissborg.cryptomarket.filter

import com.swissborg.cryptomarket.extension.empty
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ContainsTextFilterTest {

    private val testValue = "Test_Text"
    private val testFilter = ContainsTextFilter<String> { it }

    @Test
    fun `test text filter, when source has searched text in start, then true is returned`() {
        assertTrue(testFilter.apply(testValue, "Test"))
    }

    @Test
    fun `test text filter, when source has searched text in middle, then true is returned`() {
        assertTrue(testFilter.apply(testValue, "Text"))
    }

    @Test
    fun `test text filter, when source has searched text in end, then true is returned`() {
        assertTrue(testFilter.apply(testValue, "t_T"))
    }

    @Test
    fun `test text filter, when source has searched text but with different case, then true is returned`() {
        assertTrue(testFilter.apply(testValue, "tESt"))
    }

    @Test
    fun `test text filter, when source does not have searched text in, then false is returned`() {
        assertFalse(testFilter.apply(testValue, "abc"))
    }

    @Test
    fun `test text filter, when source is empty, then false is returned`() {
        assertFalse(testFilter.apply(String.empty, "abc"))
    }

}