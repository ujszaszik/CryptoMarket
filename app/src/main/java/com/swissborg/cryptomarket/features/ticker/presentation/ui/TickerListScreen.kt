package com.swissborg.cryptomarket.features.ticker.presentation.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.swissborg.cryptomarket.compose.layout.LoadingBox
import com.swissborg.cryptomarket.compose.screen.filteredlist.FilteredListScreen
import com.swissborg.cryptomarket.compose.screen.refresh.RefreshableScreen
import com.swissborg.cryptomarket.coroutines.collectAsStateValue
import com.swissborg.cryptomarket.extension.orZero
import com.swissborg.cryptomarket.features.ticker.domain.mapper.TickerUiModels
import com.swissborg.cryptomarket.features.ticker.presentation.viewmodel.TickerListViewModel
import com.swissborg.cryptomarket.filter.ContainsTextFilter
import com.swissborg.cryptomarket.navigation.Host
import org.threeten.bp.LocalDateTime

val TickerListScreen = Host(route = "TickerListScreen")

@Composable
fun TickerListScreen(viewModel: TickerListViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateValue()

    var listToShow by remember { mutableStateOf<TickerUiModels?>(null) }

    LaunchedEffect(state) {
        listToShow = state?.tickers
    }

    RefreshableScreen(
        refreshRate = state?.remainingSecs.orZero(),
        lastUpdated = state?.lastUpdated ?: LocalDateTime.now(),
        errorMessage = state?.error
    ) {

        LoadingBox(isLoading = true == state?.isLoading) {

            val hasError = true == state?.error?.isNotEmpty()

            if (listToShow != null) {

                FilteredListScreen(
                    originalList = { listToShow!! },
                    filter = ContainsTextFilter { it.currency.displayName },
                    hasError = true == state?.error?.isNotEmpty(),
                    itemContent = { TickerListItemScreen(it) },
                    emptyContent = { TickerListEmptyScreen() }
                )
            } else if (hasError) TickerListEmptyScreen()

        }
    }
}