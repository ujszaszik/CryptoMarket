package com.swissborg.cryptomarket.features.ticker.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swissborg.cryptomarket.compose.layout.fillRemainingSpace
import com.swissborg.cryptomarket.resources.Colors
import com.swissborg.cryptomarket.resources.Dimens
import com.swissborg.cryptomarket.resources.Shapes
import com.swissborg.cryptomarket.resources.Typography

@Composable
fun TickerListItemScreen(model: TickerListUiModel) {
    Card(
        modifier = Modifier.padding(Dimens.paddingDefault),
        shape = Shapes.default.large,
        elevation = Dimens.defaultCardElevation,
        backgroundColor = Colors.gray
    ) {
        Surface(
            shape = Shapes.default.large,
            modifier = Modifier.background(Colors.white)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingSmall)
                    .background(Colors.white)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TickerListCurrencyIcon(model)

                    Column(modifier = fillRemainingSpace()) {
                        Text(
                            text = model.currency.displayName,
                            style = Typography.caption
                        )
                        Text(
                            text = model.currency.id,
                            style = Typography.body2
                        )
                    }

                    Column(
                        modifier = Modifier.padding(end = Dimens.paddingDefault),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = model.asConversionRateText(),
                            style = Typography.caption
                        )
                        Text(
                            text = model.changePercentText(),
                            style = Typography.body1,
                            color = model.changePercentColor()
                        )
                    }
                }
            }
        }
    }
}
