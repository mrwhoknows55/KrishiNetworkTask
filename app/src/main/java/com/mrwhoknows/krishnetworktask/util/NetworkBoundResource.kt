package com.mrwhoknows.krishnetworktask.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline savedFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val resultFlow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            savedFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }

    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(resultFlow)
}