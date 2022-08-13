package com.nicetohave.songbook.core.network

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
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
                val tNetworkInfo by inject<NetworkInfo>()

                // Act
                testApplication {
                    val httpClient = createClient {

                    }
                    declare<HttpClient> { httpClient }
                    externalServices {
                        hosts(urlHost) {
                            this.routing {
                                get("/") {
                                    call.respond(HttpStatusCode.OK)
                                }
                            }
                        }
                    }
                    val result = tNetworkInfo.isConnected()

                    // Assert
                    result shouldBe true
                }
            }
            should("return false") {
                // Arrange
                val tNetworkInfo by inject<NetworkInfo>()

                // Act
                testApplication {
                    val httpClient = createClient {

                    }
                    declare<HttpClient> { httpClient }
                    externalServices {
                        hosts(urlHost) {
                            this.routing {
                                get("/") {
                                    call.respond(HttpStatusCode.BadRequest)
                                }
                            }
                        }
                    }
                    val result = tNetworkInfo.isConnected()

                    // Assert
                    result shouldBe false
                }
            }
        }
    }
}