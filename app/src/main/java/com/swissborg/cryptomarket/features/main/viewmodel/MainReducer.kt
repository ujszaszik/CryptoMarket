package com.swissborg.cryptomarket.features.main.viewmodel

import com.swissborg.cryptomarket.reducer.Reducer
import com.swissborg.cryptomarket.reducer.model.UiEvent
import com.swissborg.cryptomarket.reducer.model.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class MainState(val isBackPressed: Boolean = false) : UiState

@Immutable
sealed class MainEvent : UiEvent {
    object BackPressed : MainEvent()
    object ResetState : MainEvent()
}

class MainReducer(initialState: MainState) : Reducer<MainState, MainEvent>(initialState) {

    override fun reduce(oldState: MainState, event: MainEvent) {
        when (event) {

            is MainEvent.BackPressed -> {
                setState(oldState.copy(isBackPressed = true))
            }

            is MainEvent.ResetState -> {
                setState(MainState())
            }

        }
    }
}