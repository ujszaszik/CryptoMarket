package com.swissborg.cryptomarket.filter

interface Filter<Source, Type> {

    fun apply(source: Source, type: Type): Boolean
}