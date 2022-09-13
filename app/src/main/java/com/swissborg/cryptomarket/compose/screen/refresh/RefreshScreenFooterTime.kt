package com.swissborg.cryptomarket.compose.screen.refresh

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swissborg.cryptomarket.compose.layout.FillRemainingSpace
import com.swissborg.cryptomarket.resources.Dimens
import com.swissborg.cryptomarket.resources.Fonts
import org.threeten.bp.LocalDateTime

@Composable
fun RefreshScreenFooterTime(
    lastRefreshed: LocalDateTime,
    countDownTimeSecs: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.footerHeight)
            .padding(
                start = Dimens.paddingDouble,
                end = Dimens.paddingDouble
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = getLastUpdatedText(lastRefreshed),
            fontSize = Dimens.defaultFontSize,
            fontFamily = Fonts.common
        )

        FillRemainingSpace()

        Text(
            text = getNextUpdateText(countDownTimeSecs),
            fontSize = Dimens.defaultFontSize,
            fontFamily = Fonts.common
        )
    }

}