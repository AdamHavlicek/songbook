package com.nicetohave.songbook.core.enums

enum class NoteAccidental {
    SHARP,  // INFO: ♯
    FLAT,   // INFO: ♭
    NATURAL // INFO: ♮ - default
}

fun NoteAccidental.doubleFlat(): Pair<NoteAccidental, NoteAccidental> {
    return NoteAccidental.FLAT to NoteAccidental.FLAT
}

fun NoteAccidental.doubleSharp(): Pair<NoteAccidental, NoteAccidental> {
    return NoteAccidental.SHARP to NoteAccidental.SHARP
}
