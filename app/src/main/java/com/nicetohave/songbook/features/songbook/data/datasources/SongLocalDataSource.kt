package com.nicetohave.songbook.features.songbook.data.datasources

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.core.annotation.Single
import java.io.IOException
import com.squareup.moshi.Moshi.Builder as MoshiBuilder

abstract class SongLocalDataSource {
    // TODO: Song Model
    abstract fun getSelectedSong(id: Int)
}

@Single
class SongLocalDataSourceImpl(
    private val moshiMoshi: MoshiBuilder,
    private val context: Context
) : SongLocalDataSource() {

    fun getMoshi(moshiBuilder: MoshiBuilder): Moshi {
        return moshiBuilder.build()
    }

    fun getJsonDataFromAsset(fileName: String): String? {
        return try {
            context
                .assets
                .open("song")
                .bufferedReader()
                .use {
                    val text = it.readText()
                    it.close()

                    text
                }
        } catch (exception: IOException) {
            return null
        }
    }

    override fun getSelectedSong(id: Int) {
        val moshi = getMoshi(moshiMoshi)
    }
}
