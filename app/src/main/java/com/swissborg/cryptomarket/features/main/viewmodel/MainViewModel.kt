package com.swissborg.cryptomarket.features.main.viewmodel

import com.swissborg.cryptomarket.reducer.ReducerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ReducerViewModel<MainState, MainEvent>() {

    override val reducer = MainReducer(MainState())

    internal fun onBackPressed() = reducer.sendEvent(MainEvent.BackPressed)

    internal fun resetState() = reducer.sendEvent(MainEvent.ResetState)
}