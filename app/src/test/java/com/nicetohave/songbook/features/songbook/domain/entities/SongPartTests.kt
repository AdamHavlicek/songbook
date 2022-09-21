package com.nicetohave.songbook.features.songbook.domain.entities

import com.nicetohave.songbook.core.enums.SongPartType
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

internal class SongPartTests : ShouldSpec({
    context("[CreateChorus]") {
        should("create an instance of [SongPart] entity with type [SongPartType.CHORUS]") {
            // Arrange
            val id = -1
            val songId = 0
            val lyrics = listOf(
                "Nebe je modrý a zlatý, bílá sluneční záře,",
                "horko a sváteční šaty, vřava a zpocený tváře,",
                "vím, co se bude dít, býk už se v ohradě vzpíná,",
                "kdo chce, ten může jít, já si dám sklenici vína."
            )
            // Act
            val result = SongPart.createChorus(
                id = id,
                songId = songId,
                lyrics = lyrics
            )
            // Assert
            result shouldBe instanceOf<SongPart>()
            result.type shouldBe SongPartType.CHORUS
            result.id shouldBeExactly id
            result.songId shouldBeExactly songId
            result.lyrics shouldBe lyrics
        }
    }

    context("[CreateVerse]") {
        should("create an instance of [SongPart] entity with type [SongPartType.VERSE]") {
            // Arrange
            val id = -1
            val songId = 0
            val lyrics = listOf(
                "Žízeň je veliká, život mi utíká,",
                "nechte mě příjemně snít, ",
                "ve stínu pod fíky poslouchat slavíky, ",
                "zpívat si s nima a pít. "
            )
            // Act
            val result = SongPart.createVerse(
                id = id,
                songId = songId,
                lyrics = lyrics
            )

            // Assert
            result shouldBe instanceOf<SongPart>()
            result.type shouldBe SongPartType.VERSE
            result.id shouldBeExactly id
            result.songId shouldBeExactly songId
            result.lyrics shouldBe lyrics
        }
    }
})