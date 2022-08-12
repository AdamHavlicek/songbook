package com.nicetohave.songbook.features.songbook.domain.usecases

import com.nicetohave.songbook.features.songbook.data.repositories.SongRepositoryImpl
import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.fail

class GetSongUseCasesTests : ShouldSpec(), KoinTest {

    override fun extensions(): List<Extension> = listOf(KoinExtension(
        module = module {
            single<ISongRepository> {
                SongRepositoryImpl()
            }
            single {
                GetSongUseCase(
                    songRepository = get()
                )
            }
        },
        mode = KoinLifecycleMode.Root
    ),
    )

    private val tGetSongUseCase by inject<GetSongUseCase>()

    init {
        context("[GetSongUseCase]") {
            should("be inject with fake repository") {
                // Arrange
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