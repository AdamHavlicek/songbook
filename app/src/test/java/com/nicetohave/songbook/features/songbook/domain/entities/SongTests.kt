package com.nicetohave.songbook.features.songbook.domain.entities

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

internal class SongTests : ShouldSpec({
    context("Constructor") {
        should("create an instance of [Song] domain entity") {
            // Arrange
            val id = 0
            val title = "Slavíci z Madridu"
            val url = "/matuska-waldemar/slavici-z-madridu"
            val baseTranspose = "A-moll"
            val indexesOfVerses = listOf(
                "1",
                "R1",
                "2",
                "R1",
                "3",
                "R1"
            )
            // Act
            val result = Song(
                id = id,
                title = title,
                url = url,
                baseTranspose = baseTranspose,
                indexesOfVerses = indexesOfVerses
            )
            // Assert
            result shouldBe instanceOf<Song>()
            result.id shouldBe id
            result.title shouldBe title
            result.url shouldBe url
            result.baseTranspose shouldBe baseTranspose
            result.indexesOfVerses shouldBe indexesOfVerses
        }
    }
})
