package com.nicetohave.songbook.features.songbook.data.repositories

import com.nicetohave.songbook.features.songbook.domain.repositories.ISongRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

@Single
class SongRepositoryImpl : ISongRepository {
    override fun helloKoin(): String {
        return "Hello Koin"
    }
}
