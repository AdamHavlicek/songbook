package com.nicetohave.songbook.features.songbook.domain.repositories

import arrow.core.Either
import com.nicetohave.songbook.features.songbook.domain.entities.Song

interface ISongRepository {

    fun getSong(): Either<Exception, Song>

    fun helloKoin(): String
}