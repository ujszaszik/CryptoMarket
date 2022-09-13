package com.swissborg.cryptomarket.compose.screen.refresh

import com.swissborg.cryptomarket.extension.asFormattedText
import org.threeten.bp.LocalDateTime

internal fun getNextUpdateText(countDownTimeSecs: Int): String {
    val postfix = if (countDownTimeSecs > 1) "secs" else "sec"
    return "Next in: $countDownTimeSecs $postfix"
}

internal fun getLastUpdatedText(lastRefreshed: LocalDateTime): String {
    return "Updated: ${lastRefreshed.asFormattedText()}"
}