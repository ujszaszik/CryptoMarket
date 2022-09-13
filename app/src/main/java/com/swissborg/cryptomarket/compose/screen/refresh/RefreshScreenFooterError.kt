package com.swissborg.cryptomarket.compose.screen.refresh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swissborg.cryptomarket.resources.Colors
import com.swissborg.cryptomarket.resources.Dimens
import com.swissborg.cryptomarket.resources.Fonts

@Composable
fun RefreshScreenFooterError(
    errorMessage: String?
) {

    errorMessage?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.white)
                .height(Dimens.footerHeight)
                .padding(
                    start = Dimens.paddingDouble,
                    end = Dimens.paddingDouble
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = errorMessage,
                fontFamily = Fonts.common,
                fontSize = Dimens.defaultFontSize,
                color = Colors.red
            )
        }
    }
}