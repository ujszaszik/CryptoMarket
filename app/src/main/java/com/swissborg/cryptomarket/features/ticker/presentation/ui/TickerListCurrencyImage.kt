package com.swissborg.cryptomarket.features.ticker.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.swissborg.cryptomarket.resources.Dimens

@Composable
fun TickerListCurrencyIcon(model: TickerListUiModel) {
    Image(
        modifier = Modifier
            .padding(Dimens.paddingDouble)
            .size(Dimens.tickerListIconSize),
        painter = painterResource(model.iconId),
        contentDescription = model.currency.displayName
    )
}