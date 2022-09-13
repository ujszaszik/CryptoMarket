package com.swissborg.cryptomarket.extension

import java.math.RoundingMode
import java.text.DecimalFormat

fun Int?.orZero() = this ?: 0

fun Double.asFormattedText(): String =
    DecimalFormat("#.##")
        .apply { roundingMode = RoundingMode.DOWN }
        .format(this)

fun Double.asPercentage() = this * PCT_RATIO

private const val PCT_RATIO = 100