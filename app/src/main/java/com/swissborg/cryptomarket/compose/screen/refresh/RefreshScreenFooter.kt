package com.swissborg.cryptomarket.compose.screen.refresh

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.threeten.bp.LocalDateTime

@Composable
fun RefreshScreenFooter(
    modifier: Modifier = Modifier,
    lastRefreshed: LocalDateTime,
    countDownTimeSecs: Int,
    errorMessage: String? = null
) {

    Column(modifier = modifier.fillMaxWidth()) {
        RefreshScreenFooterError(errorMessage)
        RefreshScreenFooterTime(lastRefreshed, countDownTimeSecs)
    }
}