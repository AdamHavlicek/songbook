package com.nicetohave.songbook.features.songbook.domain.entities

import com.nicetohave.songbook.core.enums.SongPartType


data class SongPart(
    val id: Int,
    val type: SongPartType,
    val songId: Int,
    val lyrics: Collection<String>,
    val chords: Collection<Chords> = emptyList(),
) {
    companion object {
        fun createChorus(
            id: Int,
            songId: Int,
            lyrics: Collection<String>,
            chords: Collection<Chords> = emptyList(),
        ): SongPart {
            return SongPart(
                id = id,
                type = SongPartType.CHORUS,
                songId = songId,
                chords = chords,
                lyrics = lyrics
            )
        }

        fun createVerse(
            id: Int,
            songId: Int,
            lyrics: Collection<String>,
            chords: Collection<Chords> = emptyList(),
        ): SongPart {
            return SongPart(
                id = id,
                type = SongPartType.VERSE,
                songId = songId,
                chords = chords,
                lyrics = lyrics
            )
        }
    }

}