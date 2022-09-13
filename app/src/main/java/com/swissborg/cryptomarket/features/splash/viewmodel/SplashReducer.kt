package com.swissborg.cryptomarket.features.splash.viewmodel

import com.swissborg.cryptomarket.reducer.Reducer
import com.swissborg.cryptomarket.reducer.model.UiEvent
import com.swissborg.cryptomarket.reducer.model.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SplashState(
    val isLoading: Boolean = true
) : UiState

@Immutable
sealed class SplashEvent : UiEvent {
    object NavigateToSignIn : SplashEvent()
}

class SplashReducer(initialState: SplashState) :
    Reducer<SplashState, SplashEvent>(initialState) {

    override fun reduce(oldState: SplashState, event: SplashEvent) {
        when (event) {
            is SplashEvent.NavigateToSignIn -> {
                setState(oldState.copy(isLoading = false))
            }
        }
    }
}