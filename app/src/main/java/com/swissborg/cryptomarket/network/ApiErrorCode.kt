package com.swissborg.cryptomarket.network

enum class ApiErrorCode(val code: Int, val message: String) {
    DEFAULT(0, "Unexpected error happened during network call."),
    NO_CONNECTION(1, "There is no internet connection."),
    MALFORMED_RESPONSE(2, "The server returned a malformed response."),
    TIMEOUT(2, "The request has timed out."),
}