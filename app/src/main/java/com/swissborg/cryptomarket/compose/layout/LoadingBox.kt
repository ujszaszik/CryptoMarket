package com.swissborg.cryptomarket.compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.swissborg.cryptomarket.compose.progress.ProgressBar
import com.swissborg.cryptomarket.extension.empty
import kotlinx.coroutines.delay

// Avoid quickly showing and hiding progress indicator
private const val LOADING_DELAY = 100L

@Composable
fun LoadingBox(
    isLoading: Boolean,
    content: @Composable BoxScope.() -> Unit
) {

    var showIndicator by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = String.empty, block = {
        showIndicator = if (isLoading) {
            delay(LOADING_DELAY)
            true
        } else false
    })

    val loadingBoxModifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)

    Box(loadingBoxModifier) {
        content()

        if (showIndicator) CenteredColumn(loadingBoxModifier) { ProgressBar() }
    }
}