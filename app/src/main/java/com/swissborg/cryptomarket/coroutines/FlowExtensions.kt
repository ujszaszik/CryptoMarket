package com.swissborg.cryptomarket.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun ViewModel.launch(block: suspend () -> Unit) {
    viewModelScope.launch { block() }
}

@Composable
fun <T> Flow<T>.collectAsStateValue(): T? {
    return collectAsState(initial = null).value
}