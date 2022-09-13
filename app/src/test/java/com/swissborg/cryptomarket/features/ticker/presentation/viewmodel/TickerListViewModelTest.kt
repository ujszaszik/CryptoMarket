package com.swissborg.cryptomarket.features.ticker.presentation.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.swissborg.cryptomarket.features.ticker.data.model.TickerModelList
import com.swissborg.cryptomarket.features.ticker.data.repository.FakeTickerListRepository
import com.swissborg.cryptomarket.features.ticker.domain.mapper.TickerListUiModelMapper
import com.swissborg.cryptomarket.features.ticker.domain.usecase.GetTickerListUseCase
import com.swissborg.cryptomarket.network.ApiErrorCode
import com.swissborg.cryptomarket.rules.CoroutineTestRule
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class TickerListViewModelTest {

    @get:Rule(order = 1)
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 2)
    var coroutineTestRule = CoroutineTestRule()

    private val appContext = ApplicationProvider.getApplicationContext<Context>()
    private val mapper = TickerListUiModelMapper(appContext)

    private fun initViewModel(fakeTickerListRepository: FakeTickerListRepository = FakeTickerListRepository()): TickerListViewModel {

        val fakeUseCase = GetTickerListUseCase(
            repository = fakeTickerListRepository,
            mapper = mapper
        )
        return TickerListViewModel(fakeUseCase)
    }

    @Test
    fun `test viewmodel state, when returned success, then last update field is filled`() {
        runTest {
            val viewModel = initViewModel()
            val state = viewModel.reducer.state.first()
            assertNotNull(state.lastUpdated)
        }
    }

    @Test
    fun `test viewmodel state, when returned success, then error message is not filled`() {
        runTest {
            val viewModel = initViewModel()
            val state = viewModel.reducer.state.first()
            assertNull(state.error)
        }
    }

    @Test
    fun `test viewmodel state, when returned error, then error message is filled`() {
        runTest {
            val viewModel = initViewModel(FakeTickerListRepository().apply { throwError = true })
            val state = viewModel.reducer.state.first()
            assertNotNull(state.error)
        }
    }

    @Test
    fun `test viewmodel state, when returned error, then error message is appropriate`() {
        runTest {
            val viewModel = initViewModel(FakeTickerListRepository().apply { throwError = true })
            val state = viewModel.reducer.state.first()
            assertEquals(ApiErrorCode.DEFAULT.message, state.error)
        }
    }

    @Test
    fun `test viewmodel state, when returned success, then list is filled`() {
        runTest {
            val viewModel = initViewModel()
            val state = viewModel.reducer.state.first()
            assertNotNull(state.tickers)
        }
    }

    @Test
    fun `test viewmodel state, when returned success, then list has been mapped to appropriate values`() {
        runTest {
            val fakeRepository = FakeTickerListRepository()
            val viewModel = initViewModel(fakeRepository)
            val state = viewModel.reducer.state.first()
            val expectedOutcome = mapper.map(TickerModelList(fakeRepository.testModels))
            assertEquals(expectedOutcome, state.tickers)
        }
    }

    @Test
    fun `test viewmodel state, when returned error, then list has no value`() {
        runTest {
            val viewModel = initViewModel(FakeTickerListRepository().apply { throwError = true })
            val state = viewModel.reducer.state.first()
            assertNull(state.tickers)
        }
    }

}