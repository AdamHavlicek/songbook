package com.nicetohave.songbook.features.songbook.domain.usecases

import arrow.core.Either
import arrow.core.right
import com.nicetohave.songbook.core.usecases.UseCase
import com.nicetohave.songbook.features.songbook.domain.entities.Song
import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import org.koin.core.annotation.Single

typealias GetSongParam = Int

@Single
class GetSongUseCase(
    private val songRepository: ISongRepository
) : UseCase<String, GetSongParam>() {

    override suspend fun run(params: GetSongParam): Either<Exception, String> {
        return songRepository.helloKoin().right()
    }

}