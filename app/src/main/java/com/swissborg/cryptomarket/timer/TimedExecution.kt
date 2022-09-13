package com.swissborg.cryptomarket.timer

import android.os.CountDownTimer
import kotlin.math.roundToInt

fun repeatedExecution(
    refreshRate: Time,
    stepBy: Time = perSecond(),
    onTick: (Int) -> Unit,
    onExecute: () -> Unit
) {
    onExecute()
    object : CountDownTimer(refreshRate.toMillis(), stepBy.toMillis()) {

        override fun onTick(millisUntilFinished: Long) {
            onTick((millisUntilFinished / 1000.0).roundToInt())
        }

        override fun onFinish() {
            start()
            onExecute()
        }
    }.start()
}