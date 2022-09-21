package com.nicetohave.songbook.features.songbook.data.models

import com.nicetohave.songbook.core.enums.ChordType
import com.nicetohave.songbook.core.enums.Note
import com.nicetohave.songbook.core.enums.NoteAccidental

data class Chord(
    val root: Note,
    val accidental: NoteAccidental,
    val type: ChordType,
    val interval: UInt
)
