package com.swissborg.cryptomarket.network

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error<out T>(val message: String? = null) : Resource<T>()
    class Loading<out T> : Resource<T>()
}