package com.swissborg.cryptomarket.filter

interface TextFilter<Source> : Filter<Source, String>

class ContainsTextFilter<Source>(private val value: (Source) -> String) : TextFilter<Source> {

    override fun apply(source: Source, type: String): Boolean {
        return if (type.isNotEmpty()) value(source).contains(type, true)
        else true
    }
}