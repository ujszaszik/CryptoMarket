package com.swissborg.cryptomarket.reducer

import androidx.lifecycle.ViewModel
import com.swissborg.cryptomarket.reducer.model.UiEvent
import com.swissborg.cryptomarket.reducer.model.UiState
import kotlinx.coroutines.flow.Flow

abstract class ReducerViewModel<S : UiState, E : UiEvent> : ViewModel() {

    abstract val reducer: Reducer<S, E>

    val state: Flow<S>
        get() = reducer.state

}