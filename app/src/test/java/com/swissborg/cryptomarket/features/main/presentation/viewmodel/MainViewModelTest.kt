package com.swissborg.cryptomarket.features.main.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.swissborg.cryptomarket.features.main.viewmodel.MainViewModel
import com.swissborg.cryptomarket.rules.CoroutineTestRule
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule(order = 1)
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 2)
    var coroutineTestRule = CoroutineTestRule()

    private fun getViewModel() = MainViewModel()

    @Test
    fun `test viewmodel state, when initialized, then on back pressed is false in reducer state`() {
        runTest {
            val viewModel = getViewModel()
            val state = viewModel.reducer.state.first()
            assertFalse(state.isBackPressed)
        }
    }

    @Test
    fun `test viewmodel state, when on back pressed event sent, then on back pressed is true in reducer state`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.onBackPressed()
            val state = viewModel.reducer.state.first()
            assertTrue(state.isBackPressed)
        }
    }

    @Test
    fun `test viewmodel state, when reset, then on back pressed is false in reducer state`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.resetState()
            val state = viewModel.reducer.state.first()
            assertFalse(state.isBackPressed)
        }
    }
}