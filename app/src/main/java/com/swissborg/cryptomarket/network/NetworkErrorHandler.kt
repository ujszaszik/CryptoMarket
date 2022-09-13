package com.swissborg.cryptomarket.network

import com.swissborg.cryptomarket.extension.not
import timber.log.Timber
import java.net.SocketTimeoutException

object NetworkErrorHandler {

    fun <T> handleThrowable(throwable: Throwable): Resource<T> {
        Timber.e(throwable)
        return Resource.Error(getErrorMessageByThrowable(throwable))
    }

    private fun getErrorMessageByThrowable(throwable: Throwable): String {
        return when {
            !NetworkUtil.isConnected -> ApiErrorCode.NO_CONNECTION
            throwable is SocketTimeoutException -> ApiErrorCode.TIMEOUT
            throwable is MalformedResponseException -> ApiErrorCode.MALFORMED_RESPONSE
            else -> ApiErrorCode.DEFAULT
        }.message
    }
}