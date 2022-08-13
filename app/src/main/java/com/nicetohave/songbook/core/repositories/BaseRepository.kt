package com.nicetohave.songbook.core.repositories

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.nicetohave.songbook.core.network.NetworkInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

typealias DataSourceCall<T> = () -> T

abstract class BaseRepository(
    private val networkInfo: NetworkInfo
) {

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
        if (!networkInfo.isConnected()) {
            // TODO: Device failure
            return Exception("").left()
        }

        return safeCall(method)
    }
}