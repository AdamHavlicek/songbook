package com.nicetohave.songbook.core.modules

import io.ktor.client.*
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.nicetohave.songbook")
class AndroidAppModule{

    @Single
    fun httpClient(): HttpClient = HttpClient()
}
