package com.swissborg.cryptomarket.currency.model

enum class HardCurrency(
    override val id: String,
    override val displayName: String
) : Currency {
    EUR("EUR", "€"),
    USD("USD", "$");
}