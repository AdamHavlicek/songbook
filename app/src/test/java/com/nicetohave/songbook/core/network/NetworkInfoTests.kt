package com.nicetohave.songbook.core.network

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
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declare

class NetworkInfoTests : ShouldSpec(), KoinTest {

    private val urlHost = "https://1.1.1.1"
    private val module = module {
        single<NetworkInfo> {
            NetworkInfoImpl(
                httpClient = get(),
                urlHost = urlHost
            )
        }
        single {
            HttpClient(engine = get())
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
        context("[isConnected]") {
            should("return true") {
                // Arrange
                declare<HttpClientEngine> {
                    MockEngine { _ ->
                        respondOk("")
                    }
                }
                val tNetworkInfo by inject<NetworkInfo>()

                // Act
                val result = tNetworkInfo.isConnected()

                // Assert
                result shouldBe true
            }
            should("return false") {
                // Arrange
                declare<HttpClientEngine> {
                    MockEngine { _ ->
                        respondError(HttpStatusCode.BadRequest)
                    }
                }
                val tNetworkInfo by inject<NetworkInfo>()

                // Act
                val result = tNetworkInfo.isConnected()

                // Assert
                result shouldBe false
            }
        }
    }
}