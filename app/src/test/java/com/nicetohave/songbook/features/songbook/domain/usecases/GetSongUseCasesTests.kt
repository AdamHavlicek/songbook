package com.nicetohave.songbook.features.songbook.domain.usecases

import com.nicetohave.songbook.features.songbook.data.repositories.SongRepositoryImpl
import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declare
import kotlin.test.fail

class GetSongUseCasesTests : ShouldSpec(), KoinTest {

    override fun extensions(): List<Extension> = listOf(
        KoinExtension(
            module = module {
                singleOf<ISongRepository>(::SongRepositoryImpl)
                singleOf(::GetSongUseCase)
            },
            mode = KoinLifecycleMode.Root
        ),
    )

    private val tGetSongUseCase by inject<GetSongUseCase>()

    init {
        context("[GetSongUseCase]") {
            should("be inject with fake repository") {
                // Arrange
                declare {

                }

                // Act
                tGetSongUseCase(
                    1,
                    {
                        // Assert
                        it shouldBe "Hello Koin"
                    },
                    {
                        fail(it.toString())
                    }
                )
            }
        }
    }
}