package com.nicetohave.songbook.core.network

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.nicetohave.songbook.core.exception.BaseException
import com.nicetohave.songbook.core.exception.NetworkException
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Property
import org.koin.core.annotation.Single

abstract class NetworkInfo {
    abstract suspend fun isConnected(): Boolean
}

@Single
class NetworkInfoImpl(
    private val httpClient: HttpClient,
    @Property("network_info_host")
    private val urlHost: String
) : NetworkInfo() {

    private suspend fun getResponseCodeAsync(): Deferred<Either<NetworkException, HttpStatusCode>> {
        return coroutineScope {
            async {
                try {
                    httpClient.get(
                        Url(urlHost)
                    ).status.right()

                } catch (exception: Exception) {
                    val message = exception.message
                    NetworkException(message = message.orEmpty()).left()
                }
            }
        }
    }

    override suspend fun isConnected(): Boolean {
        return getResponseCodeAsync().await().fold(
            ifLeft = { throw it },
            ifRight = { statusCode -> statusCode == HttpStatusCode.OK }
        )
    }
}