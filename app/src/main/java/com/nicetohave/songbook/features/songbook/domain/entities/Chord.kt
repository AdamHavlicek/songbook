package com.nicetohave.songbook.features.songbook.domain.entities

import com.nicetohave.songbook.core.enums.ChordType
import com.nicetohave.songbook.core.enums.Note
import com.nicetohave.songbook.core.enums.NoteAccidental

interface IChord {
    val rootNote: Note
    val accidental: Pair<NoteAccidental, NoteAccidental?>
    val type: ChordType
    val interval: UInt // e.g. 1 - 13
}

interface IWithExtraNote {
    val extraNote: IChord
}

sealed class ChordBase(
    override val rootNote: Note,
    override val accidental: Pair<NoteAccidental, NoteAccidental?>,
    override val type: ChordType,
    override val interval: UInt,
) : IChord

data class ChordRoot(
    // TODO: implement note quality
    override val rootNote: Note,
    override val accidental: Pair<NoteAccidental, NoteAccidental?>,
    override val interval: UInt,
    override val extraNote: IChord
) : ChordBase(
    rootNote = rootNote,
    accidental = accidental,
    type = ChordType.NONE,
    interval = interval
), IWithExtraNote

data class ChordSlash(
    override val rootNote: Note,
    override val accidental: Pair<NoteAccidental, NoteAccidental?>,
    override val interval: UInt = 0.toUInt(),
) : ChordBase(
    rootNote = rootNote,
    accidental = accidental,
    type = ChordType.SLASH,
    interval = interval
)

data class ChordSuspended(
    override val rootNote: Note,
    override val accidental: Pair<NoteAccidental, NoteAccidental?>,
    override val interval: UInt,
) : ChordBase(
    rootNote = rootNote,
    accidental = accidental,
    type = ChordType.SUSPENDED,
    interval = interval
)

data class ChordAdded(
    override val rootNote: Note,
    override val accidental: Pair<NoteAccidental, NoteAccidental?>,
    override val interval: UInt,
) : ChordBase(
    rootNote = rootNote,
    accidental = accidental,
    type = ChordType.ADDED,
    interval = interval
)
