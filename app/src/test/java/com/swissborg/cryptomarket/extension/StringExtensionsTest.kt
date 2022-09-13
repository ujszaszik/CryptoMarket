package com.swissborg.cryptomarket.extension

import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StringExtensionsTest {

    @Test
    fun `test remove colons, when text has no colons, then nothing is removed`() {
        val test = "nocolonisthistext"
        assertTrue(test == test.removeColons())
    }

    @Test
    fun `test remove colons, when text has one colon inside, then it is removed`() {
        val test = "theris:colonisthistext"
        val expectedOutcome = "theriscolonisthistext"
        assertTrue(expectedOutcome == test.removeColons())
    }

    @Test
    fun `test remove colons, when text has leading colon, then it is removed`() {
        val test = ":theriscolonisthistext"
        val expectedOutcome = "theriscolonisthistext"
        assertTrue(expectedOutcome == test.removeColons())
    }

    @Test
    fun `test remove colons, when text has trailing colon, then it is removed`() {
        val test = "theriscolonisthistext:"
        val expectedOutcome = "theriscolonisthistext"
        assertTrue(expectedOutcome == test.removeColons())
    }

    @Test
    fun `test remove colons, when text has multiple colons, then they are removed`() {
        val test = "::there:is:colon:is:this::text:"
        val expectedOutcome = "thereiscolonisthistext"
        assertTrue(expectedOutcome == test.removeColons())
    }

    @Test
    fun `test remove colons, when text has only colons, then empty text is returned`() {
        val test = "::::::::"
        assertTrue(String.empty == test.removeColons())
    }
}