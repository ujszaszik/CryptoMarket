package com.swissborg.cryptomarket.extension

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.atomic.AtomicBoolean

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BooleanExtensionsTest {

    @Test
    fun `test atomic boolean, when ++ called for false, then value is true`() {
        var testBool = AtomicBoolean(false)
        testBool++
        assertTrue(testBool.get())
    }

    @Test
    fun `test atomic boolean, when ++ called for true, then value is still true`() {
        var testBool = AtomicBoolean(true)
        testBool++
        assertTrue(testBool.get())
    }

    @Test
    fun `test atomic boolean, when -- called for true, then value is false`() {
        var testBool = AtomicBoolean(true)
        testBool--
        assertFalse(testBool.get())
    }

    @Test
    fun `test atomic boolean, when -- called for false, then value is still false`() {
        var testBool = AtomicBoolean(false)
        testBool--
        assertFalse(testBool.get())
    }

    @Test
    fun `test atomic boolean, when ! called for true, then value is false`() {
        var testBool = AtomicBoolean(true)
        assertFalse(!testBool)
    }

    @Test
    fun `test atomic boolean, when ! called for false, then value is true`() {
        var testBool = AtomicBoolean(false)
        assertTrue(!testBool)
    }

}