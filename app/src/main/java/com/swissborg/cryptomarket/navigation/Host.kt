package com.swissborg.cryptomarket.navigation

import com.swissborg.cryptomarket.extension.empty

data class Host(
    var route: String,
    var title: String = String.empty
)