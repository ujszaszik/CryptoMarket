package com.swissborg.cryptomarket.features.ticker.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.swissborg.cryptomarket.R
import com.swissborg.cryptomarket.compose.layout.CenteredColumn
import com.swissborg.cryptomarket.resources.Dimens
import com.swissborg.cryptomarket.extension.empty
import kotlinx.coroutines.delay

// Avoid showing too early when Loading state transitions into showing parsed models
private const val EMPTY_LIST_DELAY = 300L

@Composable
fun TickerListEmptyScreen() {

    var showScreen by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = String.empty, block = {
        delay(EMPTY_LIST_DELAY)
        showScreen = true
    })

    if (showScreen) {
        CenteredColumn(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.size(Dimens.largeIconSize)) {
                val lottieSpec = LottieCompositionSpec.RawRes(R.raw.empty_box)
                val composition by rememberLottieComposition(lottieSpec)
                LottieAnimation(
                    composition = composition,
                    iterations = 1
                )
            }
        }
    }
}