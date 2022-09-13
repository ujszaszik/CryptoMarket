package com.swissborg.cryptomarket.features.ticker.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import com.swissborg.cryptomarket.coroutines.ResourceFlowMediator
import com.swissborg.cryptomarket.currency.CurrencyConversionSymbolGenerator
import com.swissborg.cryptomarket.currency.currencyConversionSymbols
import com.swissborg.cryptomarket.currency.model.HardCurrency
import com.swissborg.cryptomarket.features.ticker.domain.usecase.GetTickerListUseCase
import com.swissborg.cryptomarket.reducer.ReducerViewModel
import com.swissborg.cryptomarket.timer.Time
import com.swissborg.cryptomarket.timer.repeatedExecution
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class TickerListViewModel @Inject constructor(
    private val getTickerListUseCase: GetTickerListUseCase
) : ReducerViewModel<TickerListState, TickerListEvent>() {

    override val reducer = TickerListReducer(TickerListState())

    init {
        loadTickersRepeatedly()
    }

    private fun loadTickersRepeatedly() {
        repeatedExecution(
            refreshRate = REFRESH_RATE,
            onTick = { reducer.sendEvent(TickerListEvent.UpdateRemainingTime(it)) },
            onExecute = { loadTickers() }
        )
    }

    private fun loadTickers() {
        ResourceFlowMediator(
            source = getTickerListUseCase(getSymbols()),
            viewModel = this,
            loadEvent = { TickerListEvent.Loading },
            successEvent = { TickerListEvent.ShowTickers(it) },
            errorEvent = { TickerListEvent.ShowErrorMessage(it) }
        ).begin()
    }

    private fun getSymbols(): CurrencyConversionSymbolGenerator.Value {
        return currencyConversionSymbols {
            fromAllCrypto()
            toSingle { HardCurrency.USD }
        }
    }

    companion object {
        @VisibleForTesting
        val REFRESH_RATE = Time(5L, TimeUnit.SECONDS)
    }
}