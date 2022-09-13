package com.swissborg.cryptomarket.coroutines

import com.swissborg.cryptomarket.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias ResourceFlow<T> = Flow<Resource<T>>

fun <From, To> ResourceFlow<From>.mapResource(block: (From) -> To): ResourceFlow<To> {
    return map {
        when (it) {
            is Resource.Success -> Resource.Success(block(it.data))
            is Resource.Error -> Resource.Error(it.message)
            is Resource.Loading -> Resource.Loading()
        }
    }
}