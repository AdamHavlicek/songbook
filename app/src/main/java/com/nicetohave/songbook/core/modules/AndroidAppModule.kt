package com.nicetohave.songbook.core.modules

import io.ktor.client.HttpClient
import com.squareup.moshi.Moshi.Builder as MoshiBuilder
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.nicetohave.songbook")
class AndroidAppModule{

    @Single
    fun httpClient(): HttpClient = HttpClient()

    @Single
    fun moshiBuilder(): MoshiBuilder = MoshiBuilder()

}
