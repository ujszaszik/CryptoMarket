package com.swissborg.cryptomarket.extension

val String.Companion.empty: String
    get() = ""

val String.Companion.colon: String
    get() = ":"

val String.Companion.comma: String
    get() = ","

fun String.removeColons(): String {
    return this.replace(String.colon, String.empty)
}