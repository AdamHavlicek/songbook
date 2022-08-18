package com.nicetohave.songbook.core.usecases

import arrow.core.Either
import com.nicetohave.songbook.core.exception.Failure
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    suspend operator fun invoke(
        params: Params,
        onSuccess: (Type) -> Unit,
        onFailure: (Failure) -> Unit
    ) {
        return coroutineScope {
            async {
                val result = run(params)

                return@async result.fold(
                    ifLeft = { onFailure(it) },
                    ifRight = { onSuccess(it) }
                )
            }.await()
        }
    }

    object None
}
