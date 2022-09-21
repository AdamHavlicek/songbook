package com.nicetohave.songbook.core.scrapers

import com.nicetohave.songbook.core.enums.Note
import com.nicetohave.songbook.core.enums.NoteAccidental
import com.nicetohave.songbook.core.enums.SongPartType
import com.nicetohave.songbook.features.songbook.domain.entities.ChordSlash
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.li
import it.skrape.selects.html5.span
import it.skrape.selects.html5.ul
import java.nio.file.Paths

class FunctionsTests : ShouldSpec() {
    private val pathToResources = "src/test/resources"

    init {
        context("[getTitle]") {
            val pathToFixture = "fixtures/skrapeit/get-title.html"
            var fixtureHtml = ""

            beforeEach {
                val resource = Paths.get(pathToResources, pathToFixture)
                fixtureHtml = resource.toFile().readText()
            }

            should("return title of the song as [String]") {
                // Arrange
                // Act
                val result = htmlDocument(fixtureHtml) {
                    getTitle(this)
                }
                // Assert
                result shouldBe "Song Name"
            }
        }

        context("[getInterprets]") {
            val pathToFixture = "fixtures/skrapeit/get-interpreters.html"
            var fixtureHtml = ""

            beforeEach {
                val resource = Paths.get(pathToResources, pathToFixture);
                fixtureHtml = resource.toFile().readText()
            }

            should("return interpreter's name as [List<String>]") {
                // Arrange

                // Act
                val result = htmlDocument(fixtureHtml) {
                    getInterpreter(this)
                }

                // Assert
                result[0] shouldBe "Ilona Csáková"
            }
        }

        context("[getVerseType]") {
            val types = listOf(
                "sloka" to SongPartType.VERSE,
                "refrén" to SongPartType.CHORUS,
                "dohra" to SongPartType.FINALE,
                "bridge" to SongPartType.BRIDGE,
                "předehra" to SongPartType.OVERTURE
            )
            val pathToFixture = "fixtures/skrapeit/get-verse-type.html"
            var fixtureHtml = ""

            beforeTest {
                val resource = Paths.get(pathToResources, pathToFixture);
                fixtureHtml = resource.toFile().readText()
            }

            withData(
                nameFn = {
                    "return [SongPartType.${it.second}] based on [data-type: ${it.first}] attr in html"
                },
                types
            ) { (dataType, songVerseType) ->

                // Arrange
                val formattedFixtureHtml = fixtureHtml.replace(":data-type", dataType)

                // Act
                val result = htmlDocument(formattedFixtureHtml) {
                    ul {
                        withClass = "verses"
                        findFirst {
                            li {
                                withClass = "verse"
                                findFirst {
                                    getVerseType(this)
                                }
                            }
                        }

                    }
                }
                // Assert
                result shouldBe songVerseType
            }

            should("throw [IllegalArgumentException] when receive unknown type") {
                // Arrange
                val dataType = "Unknown Type"
                val expectedMessage = "Unhandled verse type: $dataType"
                val formattedFixtureHtml = fixtureHtml.replace(":data-type", dataType)

                // Act
                val result = shouldThrow<IllegalArgumentException> {
                    htmlDocument(formattedFixtureHtml) {
                        findFirst {
                            li {
                                withClass = "verse"
                                findFirst {
                                    getVerseType(this)
                                }
                            }
                        }

                    }
                }

                // Assert
                result.message shouldBe expectedMessage
            }
        }

        context("[getAccidental]") {
            val types = listOf(
                '♯' to NoteAccidental.SHARP,
                '♭' to NoteAccidental.FLAT,
                '♮' to NoteAccidental.NATURAL
            )

            withData(
                nameFn = {
                    "return [NoteAccidental.${it.second}] based on char: ${it.first}"
                },
                types
            ) { (charAccidental, noteAccidental) ->
                // Arrange

                // Act
                val result = getAccidental(charAccidental)

                // Assert
                result shouldBe noteAccidental
            }

            should("throw [IllegalArgumentException] when receive unknown type") {
                // Arrange
                val unknownChar = 'X'
                val expectedMessage = "Unhandled accidental type: $unknownChar"

                // Act
                val result = shouldThrow<IllegalArgumentException> {
                    getAccidental(unknownChar)
                }

                // Assert
                result.message shouldBe expectedMessage
            }
        }

        context("[getNote]") {
            val types = listOf(
                'C' to Note.C,
                'D' to Note.D,
                'E' to Note.E,
                'F' to Note.F,
                'G' to Note.G,
                'A' to Note.A,
                'H' to Note.B
            )

            withData(
                nameFn = {
                    "return [Note.${it.second}] based on char: ${it.first}"
                },
                types
            ) { (charNote, note) ->
                // Arrange
                // Act
                val result = getNote(charNote)

                // Assert
                result shouldBe note

            }

            should("throw [IllegalArgumentException] when receive unknown type") {
                // Arrange
                val unknownNote = 'X'
                val expectedMessage = "Unhandled note type: $unknownNote"

                // Act
                val result = shouldThrow<IllegalArgumentException> {
                    getNote(unknownNote)
                }

                // Assert
                result.message shouldBe expectedMessage

            }
        }

        context("[getChordSlash]") {
            val pathToFixture = "fixtures/skrapeit/get-chordslash.html"
            var fixtureHtml = ""

            beforeEach {
                val resource = Paths.get(pathToResources, pathToFixture);
                fixtureHtml = resource.toFile().readText()
            }

            should("return [ChordSlash] ") {
                // Arrange
                val expectedAccidental = NoteAccidental.SHARP to null
                val expectedInterval = 0.toUInt()
                val expectedNote = Note.F

                // Act
                val result = htmlDocument(fixtureHtml) {
                    findFirst {
                        span {
                            withClass = "verse-chord"
                            findFirst {
                                getChordSlash(this)
                            }
                        }
                    }
                }

                // Assert
                result.shouldBeInstanceOf<ChordSlash>()
                result.interval shouldBe expectedInterval
                result.accidental shouldBe expectedAccidental
                result.rootNote shouldBe expectedNote
            }
        }
    }
}