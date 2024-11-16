package com.example.pickingbomb.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

interface SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> Resource.Failure(false, throwable.code(), throwable.response()?.errorBody()?.string() ?: throwable.message())
                    is UnknownHostException, is NoRouteToHostException -> Resource.Failure(true, 0, "No Internet")
                    is SSLHandshakeException -> { Resource.Failure(false, 1, "We have blocked this ip for the security reason please contact your tech Team") }
                    is SocketTimeoutException -> Resource.Failure(false, 505, throwable.message ?: "Timeout")
                    is IOException -> Resource.Failure(false, 500, throwable.message ?: "Network Error")
                    else -> Resource.Failure(false, 501, throwable.message ?: "Unknown Error")
                }
            }
        }
    }
}
