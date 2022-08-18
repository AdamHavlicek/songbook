package com.nicetohave.songbook.features.songbook.domain.repositories

import arrow.core.Either
import com.nicetohave.songbook.core.exception.Failure
import com.nicetohave.songbook.features.songbook.domain.entities.Song

interface ISongRepository {

    fun getSongs(): Either<Failure, List<Song>>

    fun getSong(): Either<Failure, Song>

    fun updateSong(song: Song): Either<Failure, Song>

    fun createSong(song: Song): Either<Failure, Song>

    fun deleteSong(id: Int): Either<Failure, Song>

    fun helloKoin(): String
}