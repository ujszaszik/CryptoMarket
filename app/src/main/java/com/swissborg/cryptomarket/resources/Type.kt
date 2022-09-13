package com.swissborg.cryptomarket.resources

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    subtitle1 = TextStyle(
        fontFamily = Fonts.common,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    caption = TextStyle(
        fontFamily = Fonts.common,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontFamily = Fonts.common,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Fonts.common,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Colors.gray
    )
)