package com.swissborg.cryptomarket.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <Remote, Local> networkFlow(
    call: suspend () -> Remote,
    mapper: BaseMapper<Remote, Local>
): Flow<Resource<Local>> {
    return flow {
        try {
            emit(Resource.Loading())
            val result = call()
            val mappedResult = mapper.map(result)
            emit(Resource.Success(mappedResult))
        } catch (thr: Throwable) {
            emit(NetworkErrorHandler.handleThrowable(thr))
        }
    }
}