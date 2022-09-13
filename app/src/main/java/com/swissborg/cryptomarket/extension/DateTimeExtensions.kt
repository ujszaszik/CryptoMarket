package com.swissborg.cryptomarket.extension

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

private val SIMPLE_DATETIME_FORMAT_PATTERN = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss")

fun LocalDateTime.asFormattedText(): String {
    return format(SIMPLE_DATETIME_FORMAT_PATTERN)
}