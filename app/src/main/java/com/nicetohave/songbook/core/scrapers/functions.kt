package com.nicetohave.songbook.core.scrapers

import arrow.core.*
import com.nicetohave.songbook.core.enums.Note
import com.nicetohave.songbook.core.enums.NoteAccidental
import com.nicetohave.songbook.core.enums.SongPartType
import com.nicetohave.songbook.features.songbook.domain.entities.ChordSlash
import it.skrape.selects.DomTreeElement
import it.skrape.selects.ElementNotFoundException
import it.skrape.selects.html5.a
import it.skrape.selects.html5.h1
import it.skrape.selects.html5.h2
import it.skrape.selects.html5.span

fun getTitle(tree: DomTreeElement): String {
    return tree.h1 {
        withClass = "song-name"
        findFirst {
            return@findFirst this.text
        }
    }
}

fun getInterpreter(tree: DomTreeElement): List<String> {
    return tree.h2 {
        withClass = "song-interprets"
        findFirst {
            a {
                findAll {
                    return@findAll this.map { it.text }
                }
            }
        }
    }
}

fun getVerseType(tree: DomTreeElement): SongPartType {
    val songPartType = when (
        val verseType = tree.element.attr("data-type")
    ) {
        "sloka" -> SongPartType.VERSE
        "refrén" -> SongPartType.CHORUS
        "dohra" -> SongPartType.FINALE
        "bridge" -> SongPartType.BRIDGE
        "předehra" -> SongPartType.OVERTURE
        else -> throw IllegalArgumentException("Unhandled verse type: $verseType")
    }

    return songPartType
}

fun getAccidental(charAccidental: Char): NoteAccidental {
    return when (charAccidental) {
        '♯' -> NoteAccidental.SHARP
        '♭' -> NoteAccidental.FLAT
        '♮' -> NoteAccidental.NATURAL
        else -> throw IllegalArgumentException("Unhandled accidental type: $charAccidental")
    }
}

fun getAccidentalPair(accidentalString: String): Either<Exception, Pair<NoteAccidental, NoteAccidental?>> {
    // TODO: REWORK
    if (accidentalString.length !in 1..2) {
        IllegalArgumentException("Accidental string size must be between 1 to 2").left()
    }

    val accidental = arrayOfNulls<NoteAccidental>(2)

    val convertedChars = accidentalString.map { char: Char ->
        try {
            getAccidental(char).right()
        } catch (exception: IllegalArgumentException) {
            exception.left()
        }
    }

    val isLeft = convertedChars.any { it.isLeft() }

    if (isLeft) {
        return convertedChars
            .first { it.isLeft() }
            .fold(
                { it },
                { throw RuntimeException("Uncaught Exception") }
            ).left()
    }

    convertedChars.mapIndexed {index, either -> accidental[index] = either.fold({throw it}, {it}) }

    val pair = accidental[0] as NoteAccidental to accidental[1]

    return pair.right()
}

fun getNote(charNote: Char): Note {
    return when (charNote) {
        'C' -> Note.C
        'D' -> Note.D
        'E' -> Note.E
        'F' -> Note.F
        'G' -> Note.G
        'A' -> Note.A
        'H' -> Note.B
        else -> throw IllegalArgumentException("Unhandled note type: $charNote")
    }
}

fun getChordSlash(tree: DomTreeElement): ChordSlash {
    val note = tree.span {
        withClass = "chord-slash-letter"
        findFirst {
            text.trim()[0]
        }
    }

    val accidental = tree.span {
        withClass = "chord-accidental.bass-accidental"
        findFirst {
            text.trim()
        }
    }

    var interval: String

    try {
        interval = tree.span {
            withClass = "chord-index.bass-index"
            findFirst {
                text
            }
        }
    } catch (exception: ElementNotFoundException) {
        interval = "0"
    }

    return ChordSlash(
        rootNote = getNote(note),
        // TODO: fix slices to without throwing StringIndexOutOfBoundsException
        accidental = getAccidentalPair(
            accidental.plus("  ").slice(0..1).trim()
        ).fold(
            ifLeft = { throw it },
            ifRight = { it }
        ),
        interval = interval.toUInt()
    )
}