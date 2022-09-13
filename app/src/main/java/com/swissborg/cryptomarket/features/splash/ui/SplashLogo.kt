package com.swissborg.cryptomarket.features.splash.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.swissborg.cryptomarket.resources.Dimens
import com.swissborg.cryptomarket.resources.Icons
import com.swissborg.cryptomarket.features.splash.resources.SplashTexts
import com.swissborg.cryptomarket.resources.Typography

@Composable
fun SplashLogo() {
    Column {
        Icons.ApplicationIcon()
        Text(
            modifier = Modifier.padding(top = Dimens.paddingDefault),
            text = SplashTexts.APP_TITLE,
            style = Typography.subtitle1
        )
    }
}