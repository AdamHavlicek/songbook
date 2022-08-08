package com.nicetohave.songbook.features.songbook.domain.entities

enum class SongPartType {
    CHORUS,
    VERSE
}

data class SongPart(
    val id: Int,
    val type: SongPartType,
    val songId: Int,
    val lyrics: Collection<String>,
    val chords: Collection<Chords> = listOf(),
) {
    companion object {
        fun createChorus(
            id: Int,
            songId: Int,
            lyrics: Collection<String>,
            chords: Collection<Chords> = listOf(),
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
            chords: Collection<Chords> = listOf(),
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