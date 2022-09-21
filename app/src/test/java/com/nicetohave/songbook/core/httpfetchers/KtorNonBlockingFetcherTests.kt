package com.nicetohave.songbook.core.httpfetchers

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.mockk.mockkClass
import it.skrape.core.htmlDocument
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declare

class KtorNonBlockingFetcherTests : ShouldSpec(), KoinTest {

    private val urlHost = "https://pisnicky.cz"

    private val module = module {
        single<KtorNonBlockingFetcher> {
            KtorNonBlockingFetcherImpl(
                ktorClient = get()
            )
        }
        single {
            HttpClient(
                engine = get()
            )
        }
    }

    override fun extensions(): List<Extension> = listOf(
        KoinExtension(
            module = module,
            mode = KoinLifecycleMode.Test,
            mockProvider = {
                mockkClass(it, relaxed = true)
            }
        )
    )

    init {
        should("be defined") {
            // Arrange
            declare<HttpClientEngine> {
                MockEngine {
                    respondOk("")
                }
            }
            val tKtorNonBlockingFetcher by inject<KtorNonBlockingFetcher>()
            val requestBuilder = tKtorNonBlockingFetcher.requestBuilder
            // Act
            skrape(tKtorNonBlockingFetcher) {
                request {
                    url.host = urlHost
                }
                response {
                    this.htmlDocument {

                    }
                }
            }


            // Assert

        }
    }
}