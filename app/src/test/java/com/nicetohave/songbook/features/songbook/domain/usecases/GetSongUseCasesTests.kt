package com.nicetohave.songbook.features.songbook.domain.usecases

import com.nicetohave.songbook.features.songbook.data.datasources.SongLocalDataSource
import com.nicetohave.songbook.features.songbook.data.datasources.SongLocalDataSourceImpl
import com.nicetohave.songbook.features.songbook.data.repositories.SongRepositoryImpl
import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.mockkClass
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declare
import org.koin.test.mock.declareMock
import kotlin.test.fail

class GetSongUseCasesTests : ShouldSpec(), KoinTest {

    private val module = module {
        singleOf(::GetSongUseCase)
        singleOf(::SongRepositoryImpl) withOptions {
            bind<ISongRepository>()
        }
    }

    private val tGetSongUseCase by inject<GetSongUseCase>()

    override fun extensions(): List<Extension> = listOf(
        KoinExtension(
            module = module,
            mode = KoinLifecycleMode.Root,
            mockProvider = {
                mockkClass(it)
            }
        ),
    )

    init {
        context("[GetSongUseCase]") {
            should("be inject with fake repository") {
                // Arrange
                declare { }
                val mockSongLocalDataSource = declareMock<SongLocalDataSource>()

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
                // Assert
            }
        }
    }
}