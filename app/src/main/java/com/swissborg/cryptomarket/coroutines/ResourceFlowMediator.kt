package com.swissborg.cryptomarket.coroutines

import androidx.lifecycle.viewModelScope
import com.swissborg.cryptomarket.network.ApiErrorCode
import com.swissborg.cryptomarket.network.Resource
import com.swissborg.cryptomarket.reducer.ReducerViewModel
import com.swissborg.cryptomarket.reducer.model.UiEvent
import com.swissborg.cryptomarket.reducer.model.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ResourceFlowMediator<Source, State : UiState, Event : UiEvent>(
    private val source: ResourceFlow<Source>,
    private val viewModel: ReducerViewModel<State, Event>,
    private val loadEvent: () -> Event,
    private val successEvent: (Source) -> Event,
    private val errorEvent: (String) -> Event
) {

    fun begin() {
        viewModel.viewModelScope.launch {
            source.collect { resource ->
                when (resource) {
                    is Resource.Loading -> doOnLoading()
                    is Resource.Success -> doOnSuccess(resource)
                    is Resource.Error -> doOnError(resource)
                }
            }
        }

    }

    private fun doOnLoading() = viewModel.launch { viewModel.reducer.sendEvent(loadEvent()) }

    private fun doOnSuccess(resource: Resource.Success<Source>) {
        viewModel.launch {
            resource.data?.let {
                viewModel.reducer.sendEvent(successEvent(it))
            }
        }
    }

    private fun doOnError(resource: Resource.Error<Source>) {
        viewModel.launch {
            val errorMessage = resource.message ?: ApiErrorCode.DEFAULT.message
            viewModel.reducer.sendEvent(errorEvent(errorMessage))
        }
    }
}