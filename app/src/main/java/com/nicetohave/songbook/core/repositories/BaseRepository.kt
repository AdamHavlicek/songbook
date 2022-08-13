package com.nicetohave.songbook.core.repositories

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

typealias DataSourceCall<T> = () -> T

open class BaseRepository {

    private suspend fun <T>safeCall(method: DataSourceCall<T>): Either<Exception, T> {
        return try {
            coroutineScope {
                async {
                    return@async method().right()
                }
            }.await()
        } catch (exception: Exception) {
            // TODO: create failure class and method fromBaseException
            exception.left()
        }
    }

    suspend fun <T>callLocalDataSource(method: DataSourceCall<T>): Either<Exception, T> {
        return safeCall(method)
    }

    suspend fun <T>callRemoteDataSource(method: DataSourceCall<T>): Either<Exception, T> {
        // TODO: Check if device is offline
        //

        return safeCall(method)
    }
}