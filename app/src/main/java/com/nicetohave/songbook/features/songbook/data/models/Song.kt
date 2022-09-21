package com.nicetohave.songbook.features.songbook.data.models


data class Song(
    val title: String,
    val interpreter: List<String> = emptyList(),
    val url: String,
    val baseTranspose: String,
    val indexesOfVerses: List<String> = emptyList(),
    val songParts: Collection<SongPart> = emptyList()
)
