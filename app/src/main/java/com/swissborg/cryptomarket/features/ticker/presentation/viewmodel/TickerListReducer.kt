package com.swissborg.cryptomarket.features.ticker.presentation.viewmodel

import com.swissborg.cryptomarket.features.ticker.domain.mapper.TickerUiModels
import com.swissborg.cryptomarket.reducer.Reducer
import com.swissborg.cryptomarket.reducer.model.UiEvent
import com.swissborg.cryptomarket.reducer.model.UiState
import org.threeten.bp.LocalDateTime
import javax.annotation.concurrent.Immutable

@Immutable
data class TickerListState(
    val isLoading: Boolean = false,
    val tickers: TickerUiModels? = null,
    val remainingSecs: Int = 0,
    val lastUpdated: LocalDateTime = LocalDateTime.now(),
    val error: String? = null
) : UiState

@Immutable
sealed class TickerListEvent : UiEvent {
    object Loading : TickerListEvent()
    class ShowTickers(val tickers: TickerUiModels) : TickerListEvent()
    class ShowErrorMessage(val apiError: String) : TickerListEvent()
    class UpdateRemainingTime(val secs: Int) : TickerListEvent()
}

class TickerListReducer(initialState: TickerListState) :
    Reducer<TickerListState, TickerListEvent>(initialState) {

    override fun reduce(oldState: TickerListState, event: TickerListEvent) {
        when (event) {

            TickerListEvent.Loading -> {
                setState(oldState.copy(isLoading = true, error = null))
            }

            is TickerListEvent.ShowTickers -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        tickers = event.tickers,
                        error = null,
                        lastUpdated = LocalDateTime.now()
                    )
                )
            }

            is TickerListEvent.ShowErrorMessage -> {
                setState(oldState.copy(isLoading = false, error = event.apiError))
            }

            is TickerListEvent.UpdateRemainingTime -> {
                setState(oldState.copy(remainingSecs = event.secs))
            }
        }
    }
}