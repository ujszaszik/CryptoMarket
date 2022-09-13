package com.swissborg.cryptomarket.timer

import java.util.concurrent.TimeUnit

data class Time(
    val value: Long,
    val unit: TimeUnit
)

fun Time.toMillis() = unit.toMillis(value)

fun perSecond() = Time(1L, TimeUnit.SECONDS)