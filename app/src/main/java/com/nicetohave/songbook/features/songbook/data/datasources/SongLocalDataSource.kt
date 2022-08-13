package com.nicetohave.songbook.features.songbook.data.datasources

import org.koin.core.annotation.Single

abstract class SongLocalDataSource {

}

@Single
class SongLocalDataSourceImpl : SongLocalDataSource() {

}