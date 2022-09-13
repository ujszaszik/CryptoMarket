package com.swissborg.cryptomarket.extension

import java.util.concurrent.atomic.AtomicBoolean

operator fun AtomicBoolean.inc(): AtomicBoolean = this.apply { set(true) }

operator fun AtomicBoolean.dec(): AtomicBoolean = this.apply { set(false) }

operator fun AtomicBoolean.not(): Boolean = !get()