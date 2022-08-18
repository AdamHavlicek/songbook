package com.nicetohave.songbook.features.songbook.data.repositories

import arrow.core.Either
import com.nicetohave.songbook.core.exception.Failure
import com.nicetohave.songbook.core.network.NetworkInfo
import com.nicetohave.songbook.core.repositories.BaseRepository
import com.nicetohave.songbook.features.songbook.data.datasources.SongLocalDataSource
import com.nicetohave.songbook.features.songbook.domain.entities.Song
import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import org.koin.core.annotation.Single

@Single
class SongRepositoryImpl(
    private val localDataSource: SongLocalDataSource,
    private val networkInfo: NetworkInfo
) : BaseRepository(networkInfo = networkInfo), ISongRepository {
    override fun getSongs(): Either<Failure, List<Song>> {
        TODO("Not yet implemented")
    }

    override fun getSong(): Either<Failure, Song> {
        TODO("Not yet implemented")
    }

    override fun updateSong(song: Song): Either<Failure, Song> {
        TODO("Not yet implemented")
    }

    override fun createSong(song: Song): Either<Failure, Song> {
        TODO("Not yet implemented")
    }

    override fun deleteSong(id: Int): Either<Failure, Song> {
        TODO("Not yet implemented")
    }

    override fun helloKoin(): String {
        return "Hello Koin"
    }
}
