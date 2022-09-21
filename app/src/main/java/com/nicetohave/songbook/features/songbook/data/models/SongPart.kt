package com.nicetohave.songbook.features.songbook.data.models

import com.nicetohave.songbook.core.enums.SongPartType


data class SongPart(
    val type: SongPartType,
    val lyrics: List<String> = emptyList(),
    val chords: Collection<Chords> = emptyList(),
)