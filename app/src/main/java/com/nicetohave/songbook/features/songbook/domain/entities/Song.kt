package com.nicetohave.songbook.features.songbook.domain.entities

data class Song(
    val id: Int,
    val title: String,
    val url: String,
    val baseTranspose: String,
    val indexesOfVerses: Collection<String>,
    val songParts: Collection<SongPart> = listOf()
) {

}