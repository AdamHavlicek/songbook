package com.nicetohave.songbook.features.songbook.domain.entities

enum class ChordsType{
    GUITAR,
    PIANO
}

data class Chords(
    val id: Int,
    val type: ChordsType,
    val songPartId: Int,
    val chords: Collection<String>
) {
    companion object {

        fun createGuitarChords(
            id: Int,
            songPartId: Int,
            chords: Collection<String>
        ): Chords {
            return Chords(
                id = id,
                type = ChordsType.GUITAR,
                songPartId = songPartId,
                chords = chords
            )
        }

        fun createPianoChords(
            id: Int,
            songPartId: Int,
            chords: Collection<String>
        ): Chords {
            return Chords(
                id = id,
                type = ChordsType.PIANO,
                songPartId = songPartId,
                chords = chords
            )
        }
    }
}
