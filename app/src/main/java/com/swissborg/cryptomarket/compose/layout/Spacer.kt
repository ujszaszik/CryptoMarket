package com.swissborg.cryptomarket.compose.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.FillRemainingSpace() {
    Spacer(modifier = Modifier.weight(1f))
}

@SuppressLint("ComposableModifierFactory", "ModifierFactoryExtensionFunction")
@Composable
fun RowScope.fillRemainingSpace(): Modifier {
    return Modifier.weight(1f)
}