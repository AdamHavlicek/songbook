package com.nicetohave.songbook.core.httpfetchers

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import it.skrape.fetcher.Cookie
import it.skrape.fetcher.NonBlockingFetcher
import it.skrape.fetcher.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Single


sealed class KtorNonBlockingFetcher : NonBlockingFetcher<HttpRequestBuilder>

@Single
class KtorNonBlockingFetcherImpl(
    private val ktorClient: HttpClient
) : KtorNonBlockingFetcher() {

    override val requestBuilder: HttpRequestBuilder
        get() = HttpRequestBuilder()

    override suspend fun fetch(request: HttpRequestBuilder): Result {
        return coroutineScope {
            async {
                with(ktorClient.request(request)) {
                    Result(
                        responseBody = this.bodyAsText(),
                        responseStatus = Result.Status(status.value, status.description),
                        contentType = contentType()?.contentType,
                        headers = headers.toMap().mapValues { it.value.firstOrNull().orEmpty() },
                        baseUri = request.url.toString(),
                        cookies = setCookie().filterIsInstance<Cookie>()
                    )
                }
            }
        }.await()
    }
}
