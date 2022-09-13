package com.swissborg.cryptomarket.currency.model

import com.swissborg.cryptomarket.extension.colon
import com.swissborg.cryptomarket.extension.empty

interface Currency {
    val id: String
    val displayName: String

    fun needsSeparator() = id.length > 3
    fun separator() = if (needsSeparator()) String.colon else String.empty
    fun symbolPrefix() = "t${id}${separator()}"
}