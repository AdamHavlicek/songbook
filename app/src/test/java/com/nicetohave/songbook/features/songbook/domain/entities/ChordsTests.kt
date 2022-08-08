package com.nicetohave.songbook.features.songbook.domain.entities

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

internal class ChordsTests : ShouldSpec({
    context("[createGuitarChords]") {
        should("create an instance of [Chords] entity with type [ChordsType.GUITAR]") {
            // Arrange
            val id = 0
            val songPartId = 1
            val chords = listOf(
                "Ami",
                "E",
                "Ami",
                "E",
                "Ami",
                "E",
                "Ami"
            )

            // Act
            val result = Chords.createGuitarChords(
                id = id,
                songPartId = songPartId,
                chords = chords
            )

            // Assert
            result shouldBe instanceOf<Chords>()
            result.type shouldBe ChordsType.GUITAR
            result.id shouldBeExactly id
            result.songPartId shouldBeExactly songPartId
            result.chords shouldBe chords
        }

    }
    context("[createPianoChords]") {
        should("create an instance of [Chords] entity with type [ChordsType.PIANO]") {
            // Arrange
            val id = 0
            val songPartId = 1
            val chords = listOf(
                "Ami",
                "E",
                "Ami",
                "E",
                "Ami",
                "E",
                "Ami"
            )

            // Act
            val result = Chords.createPianoChords(
                id = id,
                songPartId = songPartId,
                chords = chords
            )

            // Assert
            result shouldBe instanceOf<Chords>()
            result.type shouldBe ChordsType.PIANO
            result.id shouldBeExactly id
            result.songPartId shouldBeExactly songPartId
            result.chords shouldBe chords
        }

    }
})