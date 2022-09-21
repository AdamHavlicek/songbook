package com.nicetohave.songbook.features.songbook.data.models

import com.nicetohave.songbook.features.songbook.domain.entities.ChordsType

data class Chords(
    val type: ChordsType,
    val chords: Collection<Chord> = emptyList(),
    val spaces: List<UInt> = emptyList()
)