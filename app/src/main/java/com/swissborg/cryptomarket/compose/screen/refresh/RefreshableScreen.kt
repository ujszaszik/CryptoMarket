package com.swissborg.cryptomarket.compose.screen.refresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.threeten.bp.LocalDateTime

@Composable
fun RefreshableScreen(
    refreshRate: Int,
    lastUpdated: LocalDateTime,
    errorMessage: String? = null,
    content: @Composable () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        content()
        RefreshScreenFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            errorMessage = errorMessage,
            lastRefreshed = lastUpdated,
            countDownTimeSecs = refreshRate
        )
    }
}