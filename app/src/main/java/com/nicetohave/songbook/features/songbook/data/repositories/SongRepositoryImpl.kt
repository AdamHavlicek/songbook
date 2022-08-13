package com.nicetohave.songbook.features.songbook.data.repositories

import arrow.core.Either
import com.nicetohave.songbook.core.repositories.BaseRepository
import com.nicetohave.songbook.features.songbook.data.datasources.SongLocalDataSource
import com.nicetohave.songbook.features.songbook.domain.entities.Song
import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import org.koin.core.annotation.Single

@Single
class SongRepositoryImpl(
    private val localDataSource: SongLocalDataSource
) : BaseRepository(), ISongRepository {
    override fun getSong(): Either<Exception, Song> {
        TODO("Not yet implemented")
    }

    override fun helloKoin(): String {
        return "Hello Koin"
    }
}
