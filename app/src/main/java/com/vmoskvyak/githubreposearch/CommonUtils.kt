package com.vmoskvyak.githubreposearch

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlin.coroutines.experimental.suspendCoroutine

suspend fun <T> ApolloCall<T>.await() = suspendCoroutine<Response<T>> { cont ->
    enqueue(object: ApolloCall.Callback<T>() {
        override fun onResponse(response: Response<T>) {
            cont.resume(response)
        }

        override fun onFailure(e: ApolloException) {
            cont.resumeWithException(e)
        }
    })
}
