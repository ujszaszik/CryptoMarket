package com.swissborg.cryptomarket.compose.screen.filteredlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.swissborg.cryptomarket.compose.layout.TopCenterColumn
import com.swissborg.cryptomarket.extension.empty
import com.swissborg.cryptomarket.filter.Filter
import com.swissborg.cryptomarket.resources.Dimens

@Composable
fun <T : Identifiable> FilteredListScreen(
    originalList: () -> List<T>,
    filter: (Filter<T, String>),
    hasError: Boolean = false,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable () -> Unit
) {

    var filterText by rememberSaveable { mutableStateOf(String.empty) }
    val fullList = originalList()
    var filteredList = originalList().filter { filter.apply(it, filterText) }

    TopCenterColumn {
        FilterSearchView(
            onSearchRequest = { filterValue ->
                filterText = filterValue
            },
            onResetRequest = {
                filterText = String.empty
                filteredList = fullList
            }
        )

        val bottomPadding =
            if (hasError) Dimens.paddingLarge
            else Dimens.paddingBig

        if (filteredList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(bottom = bottomPadding)
            ) {
                items(items = filteredList, key = { it.id }, itemContent = { item ->
                    itemContent(item)
                })
            }
        } else emptyContent()
    }

}