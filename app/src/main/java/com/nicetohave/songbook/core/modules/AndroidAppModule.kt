package com.nicetohave.songbook.core.modules

import io.ktor.client.*
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import com.squareup.moshi.Moshi.Builder as MoshiBuilder

@Module
@ComponentScan("com.nicetohave.songbook")
class AndroidAppModule {

    @Single
    fun httpClient(): HttpClient = HttpClient()

    @Single
    fun moshiBuilder(): MoshiBuilder = MoshiBuilder()

}
