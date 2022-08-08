package com.nicetohave.songbook.core.usecases

//import arrow.core.Either
//import kotlinx.coroutines.async
//import kotlinx.coroutines.coroutineScope

//abstract class UseCase<out Type, in Params> where Type : Any {
//
//    abstract suspend fun run(params: Params): Either<Exception, Type>
//
//    suspend operator fun invoke(
//        params: Params,
//        onSuccess: (Type) -> Unit,
//        onFailure: (Exception) -> Unit
//    ) {
//        return coroutineScope {
//            async {
//                val result = run(params)
//
//                return@async result.fold(
//                    ifLeft = { onFailure(it) },
//                    ifRight = { onSuccess(it) }
//                )
//            }.await()
//        }
//    }
//
//    object None
//}
